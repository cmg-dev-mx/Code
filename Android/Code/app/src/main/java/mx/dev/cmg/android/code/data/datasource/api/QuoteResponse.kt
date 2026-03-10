package mx.dev.cmg.android.code.data.datasource.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class QuoteResponse(
    val reason: String
)
