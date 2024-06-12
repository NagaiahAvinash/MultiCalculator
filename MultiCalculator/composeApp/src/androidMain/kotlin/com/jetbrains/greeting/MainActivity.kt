package com.jetbrains.greeting

import App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import multicalculator.composeapp.generated.resources.Res

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Sets the content view to the CalcView composable function.
        setContent {
            CalcView()
        }
    }
}

@Preview
@Composable
fun CalcView(){
    val displayText = remember { mutableStateOf("0") }
    // Creates a column with a light gray background for the calculator layout.
    Column (modifier = Modifier.background(Color.LightGray)){
        Row {
            // Displays the current value on the calculator screen.
            CalcDisplay(display = displayText)
        }
        Row {
            Column {
                // Creates rows of numeric buttons from 1 to 9.
                for (i in 7 downTo 1 step 3) {
                    CalcRow(display = displayText, startNum = i, numButtons = 3)
                }
                Row {
                    // Adds a button for the number 0 and the equals button.
                    CalcNumericButton(number = 0, display = displayText)
                    CalcEqualsButton(display = displayText)
                }
            }
            Column {
                // Adds buttons for basic arithmetic operations.
                CalcOperationButton(operation = "+", display = displayText)
                CalcOperationButton(operation = "-", display = displayText)
                CalcOperationButton(operation = "*", display = displayText)
                CalcOperationButton(operation = "/", display = displayText)
            }
        }
    }
}

@Composable
fun CalcRow(display: MutableState<String>, startNum: Int, numButtons: Int){
    // Creates a row of numeric buttons starting from the specified number.
    val endNum = startNum + numButtons
    Row(modifier = Modifier.padding(0.dp)) {
        for(i in startNum until endNum) {
            CalcNumericButton(number = i, display = display)
        }
    }
}

@Composable
fun CalcDisplay(display: MutableState<String>){
    // Displays the current value on the calculator screen.
    Text(text = display.value, modifier = Modifier
        .height(50.dp)
        .padding(5.dp)
        .fillMaxWidth())
}

@Composable
fun CalcNumericButton(number: Int, display: MutableState<String>){
    // Creates a button for a numeric value.
    Button(onClick = { display.value += number.toString() }, modifier = Modifier.padding(4.dp)) {
        Text(number.toString())
    }
}

@Composable
fun CalcOperationButton(operation: String, display: MutableState<String>){
    // Creates a button for an arithmetic operation.
    Button(onClick = { /*TODO*/ }, modifier = Modifier.padding(4.dp)) {
        Text(operation)
    }
}

@Composable
fun CalcEqualsButton(display: MutableState<String>){
    // Creates the equals button to calculate and display the result.
    Button(onClick = { display.value = "0" }, modifier = Modifier.padding(4.dp)) {
        Text("=")
    }
}


