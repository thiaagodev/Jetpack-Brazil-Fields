package dev.thiaago.jetpackbrazilfields.validators

import dev.thiaago.jetpackbrazilfields.extensions.isCPF
import org.junit.Assert.assertEquals
import org.junit.Test

class CPFValidatorTest {
    @Test
    fun test_cpf_validator_with_valid_cpfs() {
        val cpfs = listOf(
            "13005402061",
            "11677371056",
            "55364666077",
            "383.223.280-01",
            "974.948.300-65",
            "258.502.950-62",
        )

        cpfs.forEach { cpf ->
            val isValid = cpf.isCPF()

            assertEquals(true, isValid)
        }
    }

    @Test
    fun test_cpf_validator_with_invalid_cpfs() {
        val cpfs = listOf(
            "566719423049",
            "11677371078",
            "55361166077",
            "111.111.111-11",
            "222.222.222-22",
            "258.502.950-52",
        )

        cpfs.forEach { cpf ->
            val isValid = cpf.isCPF()

            assertEquals(false, isValid)
        }
    }
}