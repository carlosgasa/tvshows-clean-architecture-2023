package com.gscarlos.tvshowscarlosg.commons

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class CommonsUnitTest {

    private var dateToTest : Date? = null
    private val stringDate = "2022-01-06"


    @Before
    fun setUp() {
        val formatter = SimpleDateFormat("yyyy-mm-dd")
        dateToTest = formatter.parse(stringDate)
    }

    @Test
    fun toShortFormat_Date_returnShortFormat() {
        dateToTest?.let {
            Assert.assertEquals(stringDate, it.toShortFormat())
        }
    }

    @Test
    fun toShortFormat_Today_returnShortFormat() {
        Date().let {
            //Cambiar el dia al de la fecha actual si se quiere probar
            Assert.assertEquals("2023-03-31", it.toShortFormat())
        }
    }

    @Test
    fun toLargeFormat_Date_returnLargeFormat() {
        dateToTest?.let {
            Assert.assertEquals("jueves 6 de enero 2022", it.toLargeFormat())
        }
    }
}