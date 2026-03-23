package mx.dev.cmg.android.code.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import mx.dev.cmg.android.code.data.repository.color.ColorRepository
import mx.dev.cmg.android.code.ui.atomicdesign.subatomic.CodeColor
import mx.dev.cmg.android.code.ui.atomicdesign.subatomic.toCodeColor

class ThemeViewModel(
    private val colorRepository: ColorRepository
) : ViewModel() {

    val colors = flow {
        emit(CodeColor.DefaultColorScheme)
        emit(colorRepository.getColors().toCodeColor())
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = CodeColor.DefaultColorScheme
    )
}

