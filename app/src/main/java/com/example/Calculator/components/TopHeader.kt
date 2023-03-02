package com.example.Calculator.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.Calculator.ui.theme.Purple80

@Composable
fun TopHeader(totalValue: Double, modifier: Modifier = Modifier) {
    val totalValueFormatted = "%2.2f".format(totalValue)
    Card(
        modifier
            .height(150.dp)
            .fillMaxWidth()
            .padding(16.dp),
        backgroundColor = Purple80,
        shape = RoundedCornerShape(12.dp),
    )
    {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Total Bill Per Person",
                style = MaterialTheme.typography.h5
            )
            Text(
                text = "$$totalValueFormatted",
                fontWeight = FontWeight.ExtraBold,
                style = MaterialTheme.typography.h4
            )

        }
    }
}

@Preview
@Composable
fun headerPreview() {
    TopHeader(totalValue = 120.0)
}
