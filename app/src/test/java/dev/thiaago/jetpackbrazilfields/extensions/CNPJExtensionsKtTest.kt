package dev.thiaago.jetpackbrazilfields.extensions

import org.junit.Assert
import org.junit.Test

class CNPJExtensionsKtTest {

    @Test
    fun applyCNPJMask() {
        val cnpj = "75069361000193"
        Assert.assertEquals("75.069.361/0001-93", cnpj.applyCNPJMask())
    }
}