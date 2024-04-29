package dev.thiaago.jetpackbrazilfields.extensions

import dev.thiaago.jetpackbrazilfields.validators.CPFValidator

fun String.unmaskCPF(): String {
    return this.replace(".", "").replace("-", "")
}

fun String.applyCPFMask(): String {
    val regex = """(\d{3})(\d{3})(\d{3})(\d{2})""".toRegex()
    return regex.replace(this, "$1.$2.$3-$4")
}

fun String.isCPF(): Boolean {
    return CPFValidator.isValid(this)
}