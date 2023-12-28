package com.dioses.coupons.common.utils

import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test
import com.dioses.coupons.R
import junit.framework.TestCase.assertEquals
import org.junit.Assert.assertNotEquals

class CouponUtilsKtTest {

    @Test
    fun validateTextCodeSuccessTest() {
        val code = "WELCOME"
        assertTrue(validateTextCode(code))
    }

    @Test
    fun validateTextCodeEmptyFailTest() {
        val code = ""
        assertFalse(validateTextCode(code))
    }

    @Test
    fun validateTextCodeMinLengthTest() {
        val code = "HOLA"
        //val code2 = "HOLA2"
        assertFalse(validateTextCode(code))
        //assertTrue(validateTextCode(code2))
    }

    @Test
    fun validateTextCodeMaxLengthTest() {
        val code = "HOLAMAX"
        //val code2 = "HOLAPASOELMAX"
        assertTrue(validateTextCode(code))
        //assertFalse(validateTextCode(code2))
    }

    @Test
    fun getMsgByCodeNullTest() {
        val errorCode = null
        val expectedCode = R.string.error_unknown
        val result = getMessageErrorByCode(errorCode)
        assertEquals("Error al evaluar un null", expectedCode, result)
    }

    @Test
    fun getMsgErrorByCodeExistTest() {
        val errorCode = Constants.ERROR_EXIST
        val expectedCode = R.string.error_unique_code
        val result = getMessageErrorByCode(errorCode)
        assertEquals("Error al evaluar un cupón existente", expectedCode, result)
    }

    @Test
    fun getMsgErrorByCodeLengthTest() {
        val errorCode = Constants.ERROR_LENGTH
        val expectedCode = R.string.error_invalid_length
        val result = getMessageErrorByCode(errorCode)
        assertEquals("Error al evaluar la longitud válida", expectedCode, result)
        assertNotEquals("El recurso no existe", -1, result)
    }
}