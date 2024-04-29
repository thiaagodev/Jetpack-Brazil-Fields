package dev.thiaago.jetpackbrazilfields.validators

import dev.thiaago.jetpackbrazilfields.extensions.unmaskCNPJ

class CNPJValidator {
    companion object {
        /**
         * Validate CNPJ document
         * @param[cnpj] String (Can be a masked or unmasked CNPJ)
         * @return true if CNPJ is valid
         */
        fun isValid(cnpj: String): Boolean {
            val unmasked = cnpj.unmaskCNPJ()

            val firstTwelveDigits = unmasked.take(12)
            val verifierDigits = unmasked.takeLast(2)

            val allDigitsAreEquals = unmasked.all { char -> char == unmasked.first() }
            if (unmasked.length < 14 || allDigitsAreEquals) {
                return false
            }

            val firstVerifierDigit = calcFirstVerifierDigit(firstTwelveDigits)
            val secondVerifierDigit = calcSecondVerifierDigit(firstTwelveDigits + firstVerifierDigit)

            return "$firstVerifierDigit$secondVerifierDigit" == verifierDigits
        }

        private fun calcFirstVerifierDigit(firstTwelveDigits: String): Int {
            val digits = firstTwelveDigits.map { it.digitToInt() }.toMutableList()

            var sumOfProducts = 0
            listOf(5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2).forEach { number ->
                sumOfProducts += digits[0] * number
                digits.removeFirst()
            }

            val restOfDivisionPer11 = sumOfProducts % 11

            if (restOfDivisionPer11 == 0 || restOfDivisionPer11 == 1) return 0

            return 11 - restOfDivisionPer11
        }

        private fun calcSecondVerifierDigit(firstDigits: String): Int {
            val digits = firstDigits.map { it.digitToInt() }.toMutableList()

            var sumOfProducts = 0
            listOf(6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2).forEach { number ->
                sumOfProducts += digits[0] * number
                digits.removeFirst()
            }

            val restOfDivisionPer11 = sumOfProducts % 11

            if (restOfDivisionPer11 == 0 || restOfDivisionPer11 == 1) return 0

            return 11 - restOfDivisionPer11
        }
    }
}