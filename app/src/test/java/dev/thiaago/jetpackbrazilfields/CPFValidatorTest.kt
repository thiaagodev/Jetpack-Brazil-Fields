package dev.thiaago.jetpackbrazilfields

import dev.thiaago.jetpackbrazilfields.validators.CPFValidator
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class CPFValidatorTest {
    @Test
    fun test_cpf_validator_with_valid_cpfs() {
        val teste = CPFValidator.isValid("30375926054")
        assertEquals(4, 2 + 2)
    }
}