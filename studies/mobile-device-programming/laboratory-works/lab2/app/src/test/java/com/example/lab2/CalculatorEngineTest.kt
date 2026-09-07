package com.example.lab2

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class CalculatorEngineTest {

    @Test
    fun testExpressionAdditionAndSubtraction() {
        val res = ExpressionEvaluator.evaluate("12.5 + 7.5 - 5")
        assertTrue(res is ExpressionEvaluator.EvalResult.Success)
        assertEquals("15", (res as ExpressionEvaluator.EvalResult.Success).formatted)
    }

    @Test
    fun testExpressionPrecedence() {
        val res = ExpressionEvaluator.evaluate("2 + 3 * 4")
        assertTrue(res is ExpressionEvaluator.EvalResult.Success)
        assertEquals("14", (res as ExpressionEvaluator.EvalResult.Success).formatted)
    }

    @Test
    fun testExpressionParentheses() {
        val res = ExpressionEvaluator.evaluate("(2 + 3) * 4")
        assertTrue(res is ExpressionEvaluator.EvalResult.Success)
        assertEquals("20", (res as ExpressionEvaluator.EvalResult.Success).formatted)
    }

    @Test
    fun testPercentageSubtraction() {
        val res = ExpressionEvaluator.evaluate("70 - 10%")
        assertTrue(res is ExpressionEvaluator.EvalResult.Success)
        assertEquals("63", (res as ExpressionEvaluator.EvalResult.Success).formatted)
    }

    @Test
    fun testPercentageAddition() {
        val res = ExpressionEvaluator.evaluate("200 + 15%")
        assertTrue(res is ExpressionEvaluator.EvalResult.Success)
        assertEquals("230", (res as ExpressionEvaluator.EvalResult.Success).formatted)
    }

    @Test
    fun testPercentageMultiplication() {
        val res = ExpressionEvaluator.evaluate("50 * 20%")
        assertTrue(res is ExpressionEvaluator.EvalResult.Success)
        assertEquals("10", (res as ExpressionEvaluator.EvalResult.Success).formatted)
    }

    @Test
    fun testStandalonePercentage() {
        val res = ExpressionEvaluator.evaluate("50%")
        assertTrue(res is ExpressionEvaluator.EvalResult.Success)
        assertEquals("0.5", (res as ExpressionEvaluator.EvalResult.Success).formatted)
    }

    @Test
    fun testExpressionDivideByZero() {
        val res = ExpressionEvaluator.evaluate("15 ÷ 0")
        assertTrue(res is ExpressionEvaluator.EvalResult.Error)
        assertEquals("Помилка: ділення на нуль!", (res as ExpressionEvaluator.EvalResult.Error).message)
    }
}
