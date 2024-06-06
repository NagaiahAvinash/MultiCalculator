// This is the package declaration. It tells where this file is located in the project.
// Student ID: A00227141
// Author: Avinash Nagaiah
package com.jetbrains.greeting

// This is the Calculator class. It will contain all our calculator functions.
class Calculator {

    // This is the add function. It adds two numbers.
    fun Add(left: Int, right: Int): Int {
        // Return the sum of left and right.
        return left + right
    }

    // This is the subtract function. It subtracts the second number from the first.
    fun subtract(left: Int, right: Int): Int {
        // Return the result of left minus right.
        return left - right
    }

    // This is the multiply function. It multiplies two numbers.
    fun multiply(left: Int, right: Int): Int {
        // Return the product of left and right.
        return left * right
    }

    // This is the divide function. It divides the first number by the second.
    // It also checks if the second number is zero to avoid an error.
    fun divide(left: Int, right: Int): Int {
        // If right is zero, throw an error because division by zero is not allowed.
        if (right == 0) {
            throw IllegalArgumentException("Dividing by zero is invalid")
        }
        // Return the result of left divided by right.
        return left / right
    }
}
