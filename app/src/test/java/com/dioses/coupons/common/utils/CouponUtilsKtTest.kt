package com.dioses.coupons.common.utils

import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test
import com.dioses.coupons.R
import junit.framework.TestCase.assertEquals

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
        assertEquals(expectedCode, result)
    }
}