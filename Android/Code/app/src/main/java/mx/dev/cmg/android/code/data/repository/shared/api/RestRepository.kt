package mx.dev.cmg.android.code.data.repository.shared.api

interface RestRepository {
    suspend fun getQuote(): Result<String>
}