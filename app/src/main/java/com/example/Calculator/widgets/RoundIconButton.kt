package com.example.Calculator.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val IconButtonModifier = Modifier.size(40.dp)

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RoundIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    imageVector: ImageVector,
    tint: Color = MaterialTheme.colors.background,
    elevation: Dp = 4.dp
) {
    Card(
        modifier = modifier
            .padding(4.dp),
        onClick = onClick,
        elevation = elevation,
        backgroundColor = tint,
        shape = CircleShape
    ) {
        IconButton(
            modifier = Modifier
                .clickable {}
                .then(IconButtonModifier),
            onClick = onClick
        ) {
            Icon(
                imageVector = imageVector,
                contentDescription = imageVector.name
            )
        }

    }

}