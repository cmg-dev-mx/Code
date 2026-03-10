package mx.dev.cmg.android.code.data.datasource.api

import retrofit2.http.GET

interface ApiService {

    @GET("no")
    suspend fun getNoQuote(): QuoteResponse
}