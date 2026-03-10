package mx.dev.cmg.android.code.domain

enum class Feature(val key: String) {
    MVI("mvi"),
    CRASHLYTICS("crash"),
    PERSISTENCE("persistence"),
    SHARED("shared"),
    REST("rest")
}
