package com.example.Calculator.util

fun calculateTotalTip(bill: Double, tipPercent: Int): Double {
    return if (bill > 1 && bill.toString().isNotEmpty()) ((bill * tipPercent) / 100)
    else 0.0
}

fun calculateTotalBillPerPerson(
    bill: Double,
    totalTip: Double,
    persons: Int
): Double {
    return (bill + totalTip) / persons
}

fun stringToDouble(str: String): Double {
    return if (str.trim().isNotEmpty())
        str.toDouble() else 0.0
}