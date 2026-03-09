package mx.dev.cmg.android.code.ui.feature.main.model

import mx.dev.cmg.android.code.R
import mx.dev.cmg.android.code.domain.Feature
import mx.dev.cmg.android.code.ui.feature.main.viewmodel.MainEvent

enum class FeatureUI(
    val displayName: Int,
    val icon: Int,
    val onClickEvent: MainEvent
) {
    MVI(
        displayName = R.string.lista_nombres,
        icon = R.drawable.ic_remote_config,
        onClickEvent = MainEvent.NavigateToNameList
    ),

    CRASHLYTICS(
        displayName = R.string.provocar_crash,
        icon = R.drawable.ic_bug,
        onClickEvent = MainEvent.NavigateToCrashlytics
    ),

    PERSISTENCE(
        displayName = R.string.persistencia_datos,
        icon = R.drawable.ic_database,
        onClickEvent = MainEvent.NavigateToPersistence
    ),

    SHARED_PREFERENCES(
        displayName = R.string.shared_preferences,
        icon = R.drawable.ic_preferences,
        onClickEvent = MainEvent.NavigateToSharedPreferences
    ),

    REST_API(
        displayName = R.string.rest_api,
        icon = R.drawable.ic_api,
        onClickEvent = MainEvent.NavigateToRestApi
    );

    companion object {
        fun from(feature: Feature): FeatureUI {
            return when (feature) {
                Feature.MVI -> MVI
                Feature.CRASHLYTICS -> CRASHLYTICS
                Feature.PERSISTENCE -> PERSISTENCE
                Feature.SHARED -> SHARED_PREFERENCES
                Feature.REST -> REST_API
            }
        }
    }
}