package mx.dev.cmg.android.code.data.repository.shared.api

import mx.dev.cmg.android.code.data.datasource.api.ApiDataSource

class RestRepositoryImpl(
    private val source: ApiDataSource
) : RestRepository {

    override suspend fun getQuote(): Result<String> {
        return try {
            // Simulate API call
            val quote = "The only way to do great work is to love what you do. - Steve Jobs"
            Result.success(quote)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}