package com.example.Calculator.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.Calculator.util.calculateTotalTip
import com.example.Calculator.util.stringToDouble
import com.example.Calculator.widgets.RoundIconButton

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    billState: MutableState<Double>,
    validState: Boolean,
    keyboardController: SoftwareKeyboardController?,
    splitState: MutableState<Int>,
    sliderState: MutableState<Double>,
    tipPercent: Int,
    totalTip: MutableState<Double>
) {
    val splitRange = 0..100

    Card(
        modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(12.dp))
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, Color.Gray)
    ) {
        Column(verticalArrangement = Arrangement.Top) {
            CustomInputField(
                billState = billState,
                labelId = "Enter Bill",
                enabled = true,
                isSingleLine = true,
                onAction = KeyboardActions {
                    if (!validState) return@KeyboardActions
                    keyboardController?.hide()
                }
            )
            if (validState) {
                Row(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 12.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Split", textAlign = TextAlign.Center)
                    Spacer(modifier = Modifier.width(120.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RoundIconButton(
                            onClick = {
                                splitState.value--
                                println(splitState.value)
                                if (splitState.value !in splitRange)
                                    splitState.value = 0
                            },
                            imageVector = Icons.Default.Remove
                        )
                        Text(
                            "${splitState.value}", modifier = Modifier.padding(horizontal = 6.dp),
                            textAlign = TextAlign.Center
                        )
                        RoundIconButton(
                            onClick = {
                                splitState.value++
                                if (splitState.value !in splitRange)
                                    splitState.value = 100
                            },
                            imageVector = Icons.Default.Add
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 20.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Tip", textAlign = TextAlign.Center)
                    Spacer(modifier = Modifier.width(120.dp))
                    Text(text = "$ ${totalTip.value}")
                }
                Column(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 24.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("$tipPercent %")
                    Slider(
                        value = sliderState.value.toFloat(),
                        onValueChange = {
                            sliderState.value = it.toDouble()
                            totalTip.value = calculateTotalTip(
                                billState.value,
                                tipPercent
                            )
                        },
                        steps = 10
                    )
                }
            }
        }
    }
}

@Composable
fun CustomInputField(
    modifier: Modifier = Modifier,
    billState: MutableState<Double>,
    labelId: String,
    enabled: Boolean,
    isSingleLine: Boolean,
    keyboardType: KeyboardType = KeyboardType.Number,
    imeAction: ImeAction = ImeAction.Default,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    val billString = if (billState.value == 0.0) "" else "${billState.value}"

    OutlinedTextField(
        value = billString,
        onValueChange = {
            billState.value = stringToDouble(it)
        },
        modifier
            .padding(start = 12.dp, end = 12.dp, top = 4.dp)
            .fillMaxWidth(),
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
