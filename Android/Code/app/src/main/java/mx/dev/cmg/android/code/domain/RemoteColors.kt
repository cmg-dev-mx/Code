package mx.dev.cmg.android.code.domain

data class RemoteColors(
    val primary: Long,
    val secondary: Long,
    val tertiary: Long,
    val quaternary: Long
) {
    companion object {
        val Default = RemoteColors(
            primary = 0xFF502D24,
            secondary = 0xFFC8AC98,
            tertiary = 0xFFD45D26,
            quaternary = 0xFF6E0E0D
        )
    }
}

