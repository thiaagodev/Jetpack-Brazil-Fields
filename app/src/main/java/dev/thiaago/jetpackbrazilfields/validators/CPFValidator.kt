package dev.thiaago.jetpackbrazilfields.validators

import dev.thiaago.jetpackbrazilfields.extensions.unmaskCPF

class CPFValidator {
    companion object {
        /**
         * Validate CPF document
         * @param[cpf] String (Can be a masked or unmasked CPF)
         * @return true if CPF is valid
         */
        fun isValid(cpf: String): Boolean {
            val unmasked = cpf.unmaskCPF()

            val firstNineDigits = unmasked.substring(0, 9)

            val firstVerifierDigit = calcFirstVerifierDigit(firstNineDigits)

            println(firstVerifierDigit)
            return false
        }

        private fun calcFirstVerifierDigit(firstNineDigits: String): Int {
            val reverseFirstNineDigits =
                firstNineDigits.reversed().map { it.digitToInt() }.toMutableList()

            var sumOfProductsOfFirstNineDigits = 0
            intArrayOf(2, 3, 4, 5, 6, 7, 8, 9, 10).forEach { number ->
                sumOfProductsOfFirstNineDigits += reverseFirstNineDigits[0] * number
                reverseFirstNineDigits.removeFirst()
            }

            val verifierNumber = 11 - (sumOfProductsOfFirstNineDigits % 11)

            return if (verifierNumber >= 10) 0 else verifierNumber
        }
    }
}