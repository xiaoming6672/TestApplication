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
        val testBean = TestBean("hello", "world")
        testBean.print()
        testBean.basePrint()
    }

    @Test
    fun listTest() {
        val fruitList = listOf("Apple", "Banana", "Orange")
        for (item in fruitList.indices) {
            println(item)
        }
    }

    @Test
    fun inTest() {
        val fruitList = listOf("Apple", "Banana", "Orange")
        println("fruitList.size = ${fruitList.size}")

        val size = fruitList.size
        println("size !in fruitList.indices = ${size !in fruitList.indices}")

        val intRange = 1..5
        println("intRange.class = ${intRange.javaClass.canonicalName}")

        for (x in 1..5 step 3)
            print("$x ")
        println()

        for (x in 9 downTo 1 step 2)
            print("$x ")
        println()

        println("${intRange.reversed()}")
        for (x in intRange.reversed())
            print("$x ")
        println()
    }

    @Test
    fun whenTest() {
        val fruitList = listOf("Apple", "Banana", "Orange")

        when {
            "Apple" in fruitList -> println("Apple in list")
            "water" in fruitList -> println("water")
        }


        fun checker(obj: Any): String =
            when (obj) {
//                obj is String && obj in fruitList -> "$obj in fruitList"
                in fruitList -> "$obj in fruitList"
                "pp" -> "pp is not a fruit"
                is Int -> "input number $obj"
                else -> "unknown"
            }

        println(checker(12))
        println(checker("Banana"))
        println(checker("apple"))
        println(checker("pp"))
    }

    @Test
    fun listFilterTest() {
        val fruitList = listOf("Apple", "Awei", "Banana", "Orange")

        fruitList
            .filter {
                println(it)
                it.startsWith("A")
            }
            .sortedBy { it }
            .map { it.uppercase() }
            .forEach { println(it) }

    }

    @Test
    fun nullableTest() {
        println("123321".toInt())

        fun parseInt(str: String): Int? = str.toIntOrNull()

        val a = parseInt("8.0")
        if (a == null) {
            println("a is null")
//            return
        }


        fun getStringLength(obj: Any): Int? {
            if (obj is String) {
                // `obj` is automatically cast to `String` in this branch
                return obj.length
            }

            // `obj` is still of type `Any` outside of the type-checked branch
            return null
        }

        fun printLength(obj: Any) {
            println("Getting the length of '$obj'. Result: ${getStringLength(obj) ?: "Error: The object is not a string"} ")
        }
        printLength("Incomprehensibilities")
        printLength(1000)
        printLength(listOf(Any()))
    }

}