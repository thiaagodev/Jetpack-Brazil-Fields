package dev.thiaago.jetpackbrazilfields

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.thiaago.jetpackbrazilfields.ui.theme.JetpackBrazilFieldsTheme
import dev.thiaago.jetpackbrazilfields.ui.visualtransformations.MoneyVisualTransformation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent(content = {
            MainComposable()
        })
    }
}

@Composable
private fun MainComposable()  {
    JetpackBrazilFieldsTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                var cpf by remember { mutableStateOf("") }
                TextField(
                    value = cpf,
                    onValueChange = {
                        cpf = it
                    },
                    Modifier.border(
                        BorderStroke(2.dp, color = Color.Black),
                        shape = RoundedCornerShape(2.dp)
                    ),
                    visualTransformation = MoneyVisualTransformation()
                )
            }
        }
    }
}

@Preview
@Composable
private fun MainComposablePreview() {
    MainComposable()
}