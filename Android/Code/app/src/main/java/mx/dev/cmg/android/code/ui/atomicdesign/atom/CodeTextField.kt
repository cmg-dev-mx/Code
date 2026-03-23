package mx.dev.cmg.android.code.ui.atomicdesign.atom

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.VisualTransformation
import mx.dev.cmg.android.code.ui.atomicdesign.subatomic.CodeCustomTheme

@Composable
fun CodeTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource? = null,
    shape: Shape = TextFieldDefaults.shape
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        enabled = enabled,
        readOnly = readOnly,
        textStyle = CodeCustomTheme.Typography.body,
        label = label,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        prefix = prefix,
        suffix = suffix,
        supportingText = supportingText,
        isError = isError,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        interactionSource = interactionSource ?: remember { MutableInteractionSource() },
        shape = shape,
        colors = TextFieldDefaults.colors().copy(
            focusedIndicatorColor = CodeCustomTheme.Color.onBackground,
            unfocusedIndicatorColor = CodeCustomTheme.Color.onBackground.copy(alpha = 0.5f),
            disabledIndicatorColor = CodeCustomTheme.Color.onBackground.copy(alpha = 0.5f),
            errorIndicatorColor = CodeCustomTheme.Color.secondary,
            focusedLabelColor = CodeCustomTheme.Color.onBackground,
            unfocusedLabelColor = CodeCustomTheme.Color.onBackground.copy(alpha = 0.5f),
            disabledLabelColor = CodeCustomTheme.Color.onBackground.copy(alpha = 0.5f),
            errorLabelColor = CodeCustomTheme.Color.secondary,
            focusedTextColor = CodeCustomTheme.Color.onBackground,
            unfocusedTextColor = CodeCustomTheme.Color.onBackground,
            disabledTextColor = CodeCustomTheme.Color.onBackground.copy(alpha = 0.5f),
            errorTextColor = CodeCustomTheme.Color.secondary,
            unfocusedContainerColor = CodeCustomTheme.Color.background,
            focusedContainerColor = CodeCustomTheme.Color.background,
            disabledContainerColor = CodeCustomTheme.Color.background.copy(alpha = 0.5f),
            errorContainerColor = CodeCustomTheme.Color.background.copy(alpha = 0.5f)
        )
    )
}