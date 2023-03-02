package com.example.Calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import com.example.Calculator.components.MainContent
import com.example.Calculator.components.TopHeader
import com.example.Calculator.ui.theme.CalculatorTheme
import com.example.Calculator.util.calculateTotalBillPerPerson

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TipCalculator()
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TipCalculator() {

    val billState = remember {
        mutableStateOf(0.0)
    }
    val validState = remember(billState.value) {
        billState.value.toString().trim().isNotEmpty()
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    val sliderState = remember {
        mutableStateOf(0.0)
    }
    val tipPercent = (sliderState.value * 100).toInt()
    val splitState = remember {
        mutableStateOf(0)
    }
    val totalTip = remember {
        mutableStateOf(0.0)
    }
    val totalBillPerPerson =
        calculateTotalBillPerPerson(billState.value, totalTip.value, splitState.value)

    Column {
        TopHeader(totalValue = totalBillPerPerson)
        MainContent(
            billState = billState,
            sliderState = sliderState,
            validState = validState,
            keyboardController = keyboardController,
            splitState = splitState,
            tipPercent = tipPercent,
            totalTip = totalTip,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TipCalculatorPreview() {
    TipCalculator()
}

