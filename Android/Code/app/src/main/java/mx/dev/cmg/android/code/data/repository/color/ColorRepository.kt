package mx.dev.cmg.android.code.data.repository.color

import mx.dev.cmg.android.code.domain.RemoteColors

interface ColorRepository {
    suspend fun getColors(): RemoteColors
}

