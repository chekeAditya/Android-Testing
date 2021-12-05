package com.application.androidtesting.First

import com.application.androidtesting.First.Homework_Fibonacci_Number
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class Homework_Fibonacci_NumberTest {

    @Test
    fun `check braces`() {
        val checkBraced = Homework_Fibonacci_Number.checkBraces(
            "(a * b))"
        )
        assertThat(checkBraced).isTrue()
    }

    @Test
    fun `check Fibonacci number `() {
        val checkNumber = Homework_Fibonacci_Number.fib(
            n = 0
        )
        assertThat(checkNumber).isEqualTo(1)
    }

}