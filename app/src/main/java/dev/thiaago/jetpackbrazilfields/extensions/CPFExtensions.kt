package dev.thiaago.jetpackbrazilfields.extensions

import dev.thiaago.jetpackbrazilfields.validators.CPFValidator

fun String.unmaskCPF(): String {
    return this.replace(".", "").replace("-", "")
}

fun String.isCPF(): Boolean {
    return CPFValidator.isValid(this)
}