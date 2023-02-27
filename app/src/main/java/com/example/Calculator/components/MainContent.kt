package com.example.Calculator.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    totalBillState: MutableState<String>,
    validState: Boolean,
    keyboardController: SoftwareKeyboardController?
) {
    Card(
        modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(12.dp)),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, Color.Gray)
    ) {
        CustomInputField(
            totalBillState = totalBillState,
            labelId = "Tip",
            enabled = true,
            isSingleLine = true,
            onAction = KeyboardActions {
                if (!validState) return@KeyboardActions
                keyboardController?.hide()
            }
        )
    }
}

@Composable
fun CustomInputField(
    modifier: Modifier = Modifier,
    totalBillState: MutableState<String>,
    labelId: String,
    enabled: Boolean,
    isSingleLine: Boolean,
    keyboardType: KeyboardType = KeyboardType.Number,
    imeAction: ImeAction = ImeAction.Default,
    onAction: KeyboardActions = KeyboardActions.Default
) {

    OutlinedTextField(
        value = totalBillState.value,
        onValueChange = {
            totalBillState.value = it
        },
        modifier
            .padding(start = 12.dp, end = 12.dp, bottom = 12.dp),
        enabled = enabled,
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.AttachMoney, contentDescription = ""
            )
        },
        singleLine = isSingleLine,
        textStyle = TextStyle(MaterialTheme.colors.onBackground, 18.sp),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction,
        label = { Text(text = labelId) }
    )
}