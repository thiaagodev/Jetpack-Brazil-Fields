package dev.thiaago.jetpackbrazilfields.extensions

import org.junit.Assert
import org.junit.Test

class CPFExtensionsKtTest {

    @Test
    fun applyCPFMask() {
        val cpf = "97494830065"
        Assert.assertEquals("974.948.300-65", cpf.applyCPFMask())
    }
}