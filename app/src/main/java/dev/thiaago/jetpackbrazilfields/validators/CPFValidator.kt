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

            val firstNineDigits = unmasked.take(9)
            val verifierDigits = unmasked.takeLast(2)

            val allDigitsAreEquals = unmasked.all { char -> char == unmasked.first() }
            if(unmasked.length < 11 || allDigitsAreEquals) {
                return false
            }

            val firstVerifierDigit = calcFirstVerifierDigit(firstNineDigits)
            val secondVerifierDigit = calcSecondVerifierDigit(firstNineDigits + firstVerifierDigit)

            return "$firstVerifierDigit$secondVerifierDigit" == verifierDigits
        }

        private fun calcFirstVerifierDigit(firstNineDigits: String): Int {
            val reverseFirstNineDigits =
                firstNineDigits.reversed().map { it.digitToInt() }.toMutableList()

            var sumOfProductsOfFirstNineDigits = 0
            intArrayOf(2, 3, 4, 5, 6, 7, 8, 9, 10).forEach { number ->
                sumOfProductsOfFirstNineDigits += reverseFirstNineDigits[0] * number
                reverseFirstNineDigits.removeFirst()
            }

            val verifierDigit = 11 - (sumOfProductsOfFirstNineDigits % 11)

            return if (verifierDigit >= 10) 0 else verifierDigit
        }

        private fun calcSecondVerifierDigit(tenDigits: String): Int {
            val reverseDigits = tenDigits.reversed().map { it.digitToInt() }.toMutableList()

            var sumOfProducts = 0
            intArrayOf(2, 3, 4, 5, 6, 7, 8, 9, 10, 11).forEach { number ->
                sumOfProducts += reverseDigits[0] * number
                reverseDigits.removeFirst()
            }

            val verifierDigit = 11 - (sumOfProducts % 11)

            return if (verifierDigit >= 10) 0 else verifierDigit
        }
    }
}