package mx.dev.cmg.android.code.data.datasource.api.retrofit.service

import mx.dev.cmg.android.code.data.datasource.api.retrofit.model.QuoteResponse
import retrofit2.http.GET

interface ApiService {

    @GET("no")
    suspend fun getNoQuote(): QuoteResponse
}