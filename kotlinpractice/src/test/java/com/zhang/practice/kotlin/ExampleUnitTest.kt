package com.zhang.practice.kotlin

import com.zhang.practice.kotlin.model.TestBean
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun printTest() {
        println("printTest()>>>Hello world!")
    }

    @Test
    fun createBean() {
        val testBean = TestBean("hello")
        testBean.print()
    }
}