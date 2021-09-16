package ru.sber.functional

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PowFactoryTest {

    @Test
    fun `buildPowFunction should return lambda`() {
        assertEquals("Welcome", PowFactory.buildPowFunction(0)("Welcome"))
        assertEquals(1.0, PowFactory.buildPowFunction(0)(137))
        assertEquals(4.0, PowFactory.buildPowFunction(2)(2))
        assertEquals("aaa", PowFactory.buildPowFunction(3)('a'))
        assertEquals("KotlinKotlinKotlinKotlin", PowFactory.buildPowFunction(4)("Kotlin"))
        assertEquals(6.769201929361, PowFactory.buildPowFunction(4)(1.613))
    }
}
