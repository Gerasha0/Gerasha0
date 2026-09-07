package com.example.lab2

import java.util.Locale
import java.util.Stack
import kotlin.math.max
import kotlin.math.pow

object ExpressionEvaluator {

    sealed class EvalResult {
        data class Success(val value: Double, val formatted: String) : EvalResult()
        data class Error(val message: String) : EvalResult()
    }

    fun evaluate(expressionStr: String): EvalResult {
        val sanitized = expressionStr
            .replace("×", "*")
            .replace("÷", "/")
            .replace("−", "-")
            .replace(" ", "")

        if (sanitized.isEmpty()) {
            return EvalResult.Error("Введіть вираз")
        }

        return try {
            val rawTokens = tokenize(sanitized)
            val processedTokens = processPercentages(rawTokens)
            val rpn = infixToRPN(processedTokens)
            val resultValue = evaluateRPN(rpn)

            if (resultValue.isNaN() || resultValue.isInfinite()) {
                EvalResult.Error("Помилка обчислення")
            } else {
                EvalResult.Success(resultValue, formatResult(resultValue))
            }
        } catch (e: ArithmeticException) {
            EvalResult.Error(e.message ?: "Помилка: ділення на нуль!")
        } catch (_: Exception) {
            EvalResult.Error("Некоректний вираз")
        }
    }

    private fun tokenize(expr: String): List<String> {
        val tokens = mutableListOf<String>()
        var i = 0
        val len = expr.length

        while (i < len) {
            val c = expr[i]

            if (c.isDigit() || (c == '.')) {
                val sb = StringBuilder()
                while ((i < len) && (expr[i].isDigit() || (expr[i] == '.'))) {
                    sb.append(expr[i])
                    i++
                }
                tokens.add(sb.toString())
                continue
            }

            if (c in setOf('+', '-', '*', '/', '^', '%', '(', ')')) {
                if (c == '-') {
                    val isUnary = tokens.isEmpty() || (tokens.last() in listOf("+", "-", "*", "/", "^", "%", "("))
                    if (isUnary) {
                        i++
                        val sb = StringBuilder("-")
                        if ((i < len) && (expr[i].isDigit() || (expr[i] == '.'))) {
                            while ((i < len) && (expr[i].isDigit() || (expr[i] == '.'))) {
                                sb.append(expr[i])
                                i++
                            }
                            tokens.add(sb.toString())
                            continue
                        } else {
                            tokens.add("-1")
                            tokens.add("*")
                            continue
                        }
                    }
                }

                tokens.add(c.toString())
                i++
                continue
            }

            i++
        }

        return tokens
    }

    private fun processPercentages(initialTokens: List<String>): List<String> {
        val tokens = initialTokens.toMutableList()
        var i = 0
        while (i < tokens.size) {
            if (tokens[i] == "%") {
                val bEnd = i - 1
                if (bEnd < 0) {
                    i++
                    continue
                }
                val bStart = findOperandStart(tokens, bEnd)
                val bTokens = tokens.subList(bStart, bEnd + 1).toList()

                val opIndex = bStart - 1
                if (opIndex >= 0) {
                    val isAddOrSub = (tokens[opIndex] == "+") || (tokens[opIndex] == "-")
                    if (isAddOrSub) {
                        val aEnd = opIndex - 1
                        if (aEnd >= 0) {
                            val aStart = findOperandStart(tokens, aEnd)
                            val aTokens = tokens.subList(aStart, aEnd + 1).toList()

                            val replacement = mutableListOf("(")
                            replacement.addAll(aTokens)
                            replacement.add("*")
                            replacement.addAll(bTokens)
                            replacement.add("/")
                            replacement.add("100")
                            replacement.add(")")

                            for (k in i downTo bStart) {
                                tokens.removeAt(k)
                            }
                            tokens.addAll(bStart, replacement)
                            i = bStart + replacement.size
                            continue
                        }
                    }
                }

                val replacement = mutableListOf("(")
                replacement.addAll(bTokens)
                replacement.add("/")
                replacement.add("100")
                replacement.add(")")

                for (k in i downTo bStart) {
                    tokens.removeAt(k)
                }
                tokens.addAll(bStart, replacement)
                i = bStart + replacement.size
                continue
            }
            i++
        }
        return tokens
    }

    private fun findOperandStart(tokens: List<String>, endIndex: Int): Int {
        if (endIndex < 0) return 0
        if (tokens[endIndex] == ")") {
            var depth = 1
            var j = endIndex - 1
            while ((j >= 0) && (depth > 0)) {
                if (tokens[j] == ")") depth++
                if (tokens[j] == "(") depth--
                if (depth == 0) return j
                j--
            }
            return max(0, j)
        }
        return endIndex
    }

    private fun precedence(op: String): Int {
        return when (op) {
            "+", "-" -> 1
            "*", "/" -> 2
            "^" -> 3
            else -> 0
        }
    }

    private fun isRightAssociative(op: String): Boolean {
        return op == "^"
    }

    private fun infixToRPN(tokens: List<String>): List<String> {
        val output = mutableListOf<String>()
        val stack = Stack<String>()

        for (token in tokens) {
            val num = token.toDoubleOrNull()
            if (num != null) {
                output.add(token)
            } else if (token in listOf("+", "-", "*", "/", "^")) {
                while (stack.isNotEmpty() && (stack.peek() in listOf("+", "-", "*", "/", "^"))) {
                    val topOp = stack.peek()
                    val p1 = precedence(token)
                    val p2 = precedence(topOp)
                    val shouldPop = if (isRightAssociative(token)) p1 < p2 else p1 <= p2
                    if (shouldPop) {
                        output.add(stack.pop())
                    } else {
                        break
                    }
                }
                stack.push(token)
            } else if (token == "(") {
                stack.push(token)
            } else if (token == ")") {
                while (stack.isNotEmpty() && (stack.peek() != "(")) {
                    output.add(stack.pop())
                }
                if (stack.isNotEmpty() && (stack.peek() == "(")) {
                    stack.pop()
                } else {
                    throw IllegalArgumentException("Mismatched parentheses")
                }
            }
        }

        while (stack.isNotEmpty()) {
            val top = stack.pop()
            if ((top == "(") || (top == ")")) {
                throw IllegalArgumentException("Mismatched parentheses")
            }
            output.add(top)
        }

        return output
    }

    private fun evaluateRPN(rpn: List<String>): Double {
        val stack = Stack<Double>()

        for (token in rpn) {
            val num = token.toDoubleOrNull()
            if (num != null) {
                stack.push(num)
            } else {
                if (stack.size < 2) {
                    throw IllegalArgumentException("Invalid RPN expression")
                }
                val b = stack.pop()
                val a = stack.pop()

                val res = when (token) {
                    "+" -> a + b
                    "-" -> a - b
                    "*" -> a * b
                    "/" -> {
                        if (b == 0.0) throw ArithmeticException("Помилка: ділення на нуль!")
                        a / b
                    }
                    "^" -> a.pow(b)
                    else -> throw IllegalArgumentException("Unknown operator: $token")
                }
                stack.push(res)
            }
        }

        if (stack.size != 1) {
            throw IllegalArgumentException("Invalid RPN evaluation")
        }

        return stack.pop()
    }

    fun formatResult(result: Double): String {
        if ((result % 1.0) == 0.0) {
            if ((result >= Long.MIN_VALUE.toDouble()) && (result <= Long.MAX_VALUE.toDouble())) {
                return result.toLong().toString()
            }
        }
        return String.format(Locale.US, "%.6f", result).trimEnd('0').trimEnd('.')
    }
}