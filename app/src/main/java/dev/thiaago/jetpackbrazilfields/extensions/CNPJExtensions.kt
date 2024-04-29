package dev.thiaago.jetpackbrazilfields.extensions

import dev.thiaago.jetpackbrazilfields.validators.CNPJValidator

fun String.unmaskCNPJ(): String {
    return this.replace(".", "").replace("/", "").replace("-", "")
}

fun String.applyCNPJMask(): String {
    val regex = """(\d{2})(\d{3})(\d{3})(\d{4})(\d{2})""".toRegex()
    return regex.replace(this, "$1.$2.$3/$4-$5")
}


fun String.isCNPJ(): Boolean {
    return CNPJValidator.isValid(this)
}