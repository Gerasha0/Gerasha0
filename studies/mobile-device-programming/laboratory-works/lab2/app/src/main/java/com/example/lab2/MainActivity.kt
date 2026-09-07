package com.example.lab2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.max
import kotlin.math.min

class MainActivity : AppCompatActivity() {

    private lateinit var tvExpression: EditText
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvExpression = findViewById(R.id.tvExpression)
        tvResult = findViewById(R.id.tvResult)

        // Disable soft keyboard
        tvExpression.showSoftInputOnFocus = false

        // Digits & Dot
        val digitMap = mapOf(
            findViewById<Button>(R.id.btn0) to "0",
            findViewById<Button>(R.id.btn1) to "1",
            findViewById<Button>(R.id.btn2) to "2",
            findViewById<Button>(R.id.btn3) to "3",
            findViewById<Button>(R.id.btn4) to "4",
            findViewById<Button>(R.id.btn5) to "5",
            findViewById<Button>(R.id.btn6) to "6",
            findViewById<Button>(R.id.btn7) to "7",
            findViewById<Button>(R.id.btn8) to "8",
            findViewById<Button>(R.id.btn9) to "9",
            findViewById<Button>(R.id.btnDot) to ".",
        )

        digitMap.forEach { (button, str) ->
            button.setOnClickListener { updateText(str) }
        }

        // Operators
        findViewById<Button>(R.id.btnAdd).setOnClickListener { updateText("+") }
        findViewById<Button>(R.id.btnSubtract).setOnClickListener { updateText("−") }
        findViewById<Button>(R.id.btnMultiply).setOnClickListener { updateText("×") }
        findViewById<Button>(R.id.btnDivide).setOnClickListener { updateText("÷") }
        findViewById<Button>(R.id.btnPower).setOnClickListener { updateText("^") }
        findViewById<Button>(R.id.btnMod).setOnClickListener { updateText("%") }

        // Parentheses handler
        findViewById<Button>(R.id.btnParentheses).setOnClickListener { handleParentheses() }

        // Plus/Minus handler
        findViewById<Button>(R.id.btnPlusMinus).setOnClickListener { updateText("−") }

        // Equals (=)
        findViewById<Button>(R.id.btnEquals).setOnClickListener { evaluateExpression() }

        // Clear (C)
        findViewById<Button>(R.id.btnClear).setOnClickListener {
            tvExpression.text?.clear()
            tvResult.text = getString(R.string.result_placeholder)
            Toast.makeText(this, R.string.toast_cleared, Toast.LENGTH_SHORT).show()
        }

        // Backspace (⌫)
        findViewById<Button>(R.id.btnBackspace).setOnClickListener { performBackspace() }

        // Exit
        findViewById<Button>(R.id.btnExit).setOnClickListener {
            Toast.makeText(this, R.string.toast_exit, Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun updateText(newStr: String) {
        val oldStr = tvExpression.text.toString()
        val pos = tvExpression.selectionStart.coerceAtLeast(0)

        val leftStr = oldStr.substring(0, pos)
        val rightStr = oldStr.substring(pos)

        tvExpression.setText(String.format("%s%s%s", leftStr, newStr, rightStr))
        tvExpression.setSelection(pos + newStr.length)
    }

    private fun handleParentheses() {
        val expr = tvExpression.text.toString()
        val pos = tvExpression.selectionStart.coerceAtLeast(0)

        var openCount = 0
        var closeCount = 0

        for (i in 0 until pos) {
            if (expr[i] == '(') openCount++
            if (expr[i] == ')') closeCount++
        }

        val lastChar = if ((pos > 0) && (pos <= expr.length)) expr[pos - 1] else ' '

        if ((openCount == closeCount) || (lastChar == '(') || (lastChar in "+−×÷^%")) {
            updateText("(")
        } else if (openCount > closeCount) {
            updateText(")")
        } else {
            updateText("(")
        }
    }

    private fun evaluateExpression() {
        val expr = tvExpression.text.toString().trim()
        if (expr.isEmpty()) {
            Toast.makeText(this, R.string.error_empty_fields, Toast.LENGTH_SHORT).show()
            return
        }

        when (val res = ExpressionEvaluator.evaluate(expr)) {
            is ExpressionEvaluator.EvalResult.Success -> {
                tvResult.text = String.format("= %s", res.formatted)
            }
            is ExpressionEvaluator.EvalResult.Error -> {
                tvResult.text = res.message
                Toast.makeText(this, res.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun performBackspace() {
        val start = tvExpression.selectionStart
        val end = tvExpression.selectionEnd

        if (start != end) {
            tvExpression.text?.delete(min(start, end), max(start, end))
        } else if (start > 0) {
            tvExpression.text?.delete(start - 1, start)
        }
    }
}