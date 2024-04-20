package dev.thiaago.jetpackbrazilfields.extensions

fun String.centsToReal(): String = (this.toInt() / 100).toString()