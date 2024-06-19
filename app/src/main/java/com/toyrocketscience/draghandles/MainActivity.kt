package com.toyrocketscience.draghandles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.toyrocketscience.draghandles.ui.theme.DragHandlesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DragHandlesTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DragHandle()
                }
            }
        }
    }
}

@Composable
fun DragHandle() {
    val (textFieldValue, setTextFieldValue) = remember { mutableStateOf(TextFieldValue("Hello")) }
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    LaunchedEffect(isFocused) {
        if (isFocused)
            setTextFieldValue(
                textFieldValue.copy(
                    selection = TextRange(
                        start = 0,
                        end = textFieldValue.text.length
                    )
                )
            )
    }

    BasicTextField(
        value = textFieldValue,
        onValueChange = setTextFieldValue,
        interactionSource = interactionSource
    )
}

@Preview(showBackground = true)
@Composable
fun DragHandlePreview() {
    DragHandlesTheme {
        DragHandle()
    }
}
