package mx.dev.cmg.android.code.ui.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// Timestamp to Date in format yyyy/MM/dd - HH:mm:ss
fun Long.toFormattedDate(): String {
    val date = Date(this)
    val format = SimpleDateFormat("yyyy/MM/dd - HH:mm:ss", Locale.getDefault())
    return format.format(date)
}