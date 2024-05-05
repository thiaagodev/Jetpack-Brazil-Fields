package dev.thiaago.jetpackbrazilfields.extensions

import java.text.NumberFormat
import java.util.Locale

fun Int.centsToRealDouble(): Double = (this.toDouble() / 100)

fun Long.centsToRealDouble(): Double = (this.toDouble() / 100)

fun Double.toBrazilianCurrency(): String {
    return NumberFormat.getCurrencyInstance(Locale("pt", "br")).format(this)
}