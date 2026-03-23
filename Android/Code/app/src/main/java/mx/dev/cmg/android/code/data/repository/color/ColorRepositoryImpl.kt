package mx.dev.cmg.android.code.data.repository.color

import mx.dev.cmg.android.code.data.datasource.remoteconfig.RemoteConfigDataSource
import mx.dev.cmg.android.code.domain.RemoteColors
import org.json.JSONObject

class ColorRepositoryImpl(
    private val remoteConfigDataSource: RemoteConfigDataSource
) : ColorRepository {

    override suspend fun getColors(): RemoteColors {
        val json = remoteConfigDataSource.getString(KEY_COLORS)

        return try {
            val obj = JSONObject(json)
            RemoteColors(
                primary = obj.getString("primary").toColorLong(),
                secondary = obj.getString("secondary").toColorLong(),
                tertiary = obj.getString("tertiary").toColorLong(),
                quaternary = obj.getString("quaternary").toColorLong()
            )
        } catch (_: Exception) {
            RemoteColors.Default
        }
    }

    private fun String.toColorLong(): Long =
        removePrefix("0x").removePrefix("0X").toLong(16)

    companion object {
        const val KEY_COLORS = "colors"
        val DEFAULT_COLORS_JSON: String = """{"primary":"0xFF502D24","secondary":"0xFFC8AC98","tertiary":"0xFFD45D26","quaternary":"0xFF6E0E0D"}"""
    }
}

