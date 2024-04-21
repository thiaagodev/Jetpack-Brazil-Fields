package dev.thiaago.jetpackbrazilfields.extensions

import java.text.NumberFormat
import java.util.Locale

fun String.centsToReal(): String = (this.toInt() / 100).toString()

fun String.centsToRealDouble(): Double = (this.toInt() / 100).toDouble()

fun Double.toBrazilianCurrency(): String {
    return NumberFormat.getCurrencyInstance(Locale("pt", "br")).format(this)
}