package mx.dev.cmg.android.code.data.repository.api

interface RestRepository {
    suspend fun getQuote(): Result<String>
}