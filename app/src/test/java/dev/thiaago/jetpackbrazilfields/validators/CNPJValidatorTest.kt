package dev.thiaago.jetpackbrazilfields.validators

import dev.thiaago.jetpackbrazilfields.extensions.isCNPJ
import org.junit.Assert.assertEquals
import org.junit.Test

class CNPJValidatorTest {
    @Test
    fun test_cnpj_validator_with_valid_cnpjs() {
        val cnpjs = listOf(
            "64864032000118",
            "93606444000156",
            "00334760000135",
            "75.069.361/0001-93",
            "59.674.073/0001-01",
            "32.778.294/0001-73",
        )

        cnpjs.forEach { cnpj ->
            val isValid = cnpj.isCNPJ()

            assertEquals(true, isValid)
        }
    }

    @Test
    fun test_cnpj_validator_with_invalid_cnpjs() {
        val cnpjs = listOf(
            "64864032000114",
            "93601443000156",
            "02334760000135",
            "11.111.111/1111-11",
            "59.674.073/0001-02",
            "32.768.294/0001-73",
        )

        cnpjs.forEach { cnpj ->
            val isValid = cnpj.isCNPJ()

            assertEquals(false, isValid)
        }
    }
}