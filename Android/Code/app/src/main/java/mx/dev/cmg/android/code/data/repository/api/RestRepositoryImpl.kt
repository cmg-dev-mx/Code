package mx.dev.cmg.android.code.data.repository.api

import mx.dev.cmg.android.code.data.datasource.api.ApiDataSource

class RestRepositoryImpl(
    private val source: ApiDataSource
) : RestRepository {

    override suspend fun getQuote(): Result<String> {
        return source.getQuote()
    }
}