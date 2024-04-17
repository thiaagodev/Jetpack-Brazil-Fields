package dev.thiaago.jetpackbrazilfields.extensions

fun String.unmaskCPF(): String {
    return this.replace(".", "").replace("-", "")
}