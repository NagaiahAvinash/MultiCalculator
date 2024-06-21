// Student ID: A00227141
// Author: Avinash Nagaiah
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import multicalculator.composeapp.generated.resources.Res
import java.lang.Integer.sum
import Calculator

class MainActivity : ComponentActivity() { // Main activity class
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) // Call to superclass onCreate

        setContent {
            CalcView()
        }
    }
}

@Preview
@Composable
fun CalcView() { // This is the Main view of calculator

    var leftNumber by rememberSaveable { mutableStateOf(0) } // State for left number, preserved across recompositions
    var rightNumber by rememberSaveable { mutableStateOf(0) } // State for right number, preserved across recompositions
    var operation by rememberSaveable { mutableStateOf("") }// State for current operation, preserved across recompositions
    var complete by rememberSaveable { mutableStateOf(false) } // State to check if calculation is complete, preserved across recompositions
    val displayText = remember { mutableStateOf("0") } // State for display text, preserved across recompositions

// Check conditions for updating displayText
    if (complete && operation.isNotEmpty()) { // If calculation complete and operation not empty
        var answer = 0 // Variable to store the answer
        when (operation) { // Determine operation to calculate answer
            "+" -> answer = leftNumber + rightNumber // Perform addition
            "-" -> answer = leftNumber - rightNumber // Perform subtraction
            "*" -> answer = leftNumber * rightNumber // Perform multiplication
            "/" -> answer = if (rightNumber == 0) 0 else leftNumber / rightNumber // Perform division, handle divide by zero
        }
        displayText.value = answer.toString() // Set answer to display
    } else if (operation.isNotEmpty() && !complete) {
        displayText.value = leftNumber.toString() // Retain the current value (left number) when operator is pressed
    } else {
        displayText.value = leftNumber.toString() // Set left number to display
    }

    // Function for number button press
    fun numberPress(btnNum: Int) { // Handle number button press
        if (complete) { // If calculation is complete
            leftNumber = 0 // Reset left number
            rightNumber = 0 // Reset right number
            operation = "" // Reset operation
            complete = false // Reset complete status
        }
        if (operation.isNotBlank() && !complete) { // If operation is set and calculation not complete
            rightNumber = (rightNumber * 10) + btnNum // Update right number
            displayText.value = rightNumber.toString() // Update display text with right number
        } else if (operation.isBlank() && !complete) { // If operation not set and calculation not complete
            leftNumber = (leftNumber * 10) + btnNum // Update left number
            displayText.value = leftNumber.toString() // Update display text with left number
        }
    }

    fun operationPress(op: String) { // Handle operation button press
        if (!complete) operation = op // Set operation if calculation not complete
    }

    fun equalPress() { // Handle equal button press
        complete = true // Set calculation to complete
    }

    // UI components
    Column(modifier = Modifier.background(Color.LightGray)) { // Main column layout with light gray background
        Row { // Row for display
            CalcDisplay(display = displayText)
        }
        Row { // Row for buttons
            Column {
                for (i in 7 downTo 1 step 3) {
                    CalcRow(onPress = { numberPress(it) }, startNum = i, numButtons = 3)
                }
                Row {
                    CalcNumericButton(number = 0, onPress = { numberPress(it) })
                    CalcEqualsButton(onPress = { equalPress() })
                }
            }
            Column { // Column for operation buttons
                CalcOperationButton(operation = "+", onPress = { operationPress(it) }) // Button for addition
                CalcOperationButton(operation = "-", onPress = { operationPress(it) }) // Button for subtraction
                CalcOperationButton(operation = "*", onPress = { operationPress(it) })  // Button for multiplication
                CalcOperationButton(operation = "/", onPress = { operationPress(it) }) // Button for division
            }
        }
    }
}

@Composable
fun CalcRow(onPress: (number: Int) -> Unit, startNum: Int, numButtons: Int) { // Row of number buttons
    val endNum = startNum + numButtons
    Row(modifier = Modifier.padding(0.dp)) {
        for (i in startNum until endNum) {
            CalcNumericButton(number = i, onPress = onPress)
        }
    }
}

@Composable
fun CalcDisplay(display: MutableState<String>) { // Display component
    Text(
        text = display.value,
        modifier = Modifier
            .height(50.dp)
            .padding(5.dp)
            .fillMaxWidth()
    )
}

@Composable
fun CalcNumericButton(number: Int, onPress: (number: Int) -> Unit) { // Numeric button component
    Button(onClick = { onPress(number) }, modifier = Modifier.padding(4.dp)) { // Button for number
        Text(number.toString()) // Text of number
    }
}

@Composable
fun CalcOperationButton(operation: String, onPress: (operation: String) -> Unit) {  // Operation button component
    Button(onClick = { onPress(operation) }, modifier = Modifier.padding(4.dp)) {
        Text(operation)
    }
}

@Composable
fun CalcEqualsButton(onPress: () -> Unit) { // Equals button component
    Button(onClick = { onPress() }, modifier = Modifier.padding(4.dp)) {
        Text("=")
    }
}
