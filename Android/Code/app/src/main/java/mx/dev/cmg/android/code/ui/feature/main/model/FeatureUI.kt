package mx.dev.cmg.android.code.ui.feature.main.model

import mx.dev.cmg.android.code.R
import mx.dev.cmg.android.code.core.model.Feature
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
    );

    companion object {
        fun from(feature: Feature): FeatureUI? {
            return when (feature) {
                Feature.MVI -> MVI
            }
        }
    }
}