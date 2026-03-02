package mx.dev.cmg.android.code.domain

data class Note(
    val id: Int,
    val title: String,
    val content: String,
    val timestamp: Long
)
