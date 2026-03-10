package mx.dev.cmg.android.code.data.datasource.api

import mx.dev.cmg.android.code.data.datasource.api.retrofit.service.ApiService

class ApiDataSourceImpl(
    private val apiService: ApiService
) : ApiDataSource {

    override suspend fun getQuote(): Result<String> {
        return try {
            val response = apiService.getNoQuote()
            Result.success(response.reason)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}