package com.example.lab3

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var isOverlayMode = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val demoFrameLayout = findViewById<FrameLayout>(R.id.demoFrameLayout)
        val demoLinearLayoutContainer = findViewById<LinearLayout>(R.id.demoLinearLayoutContainer)
        val tvModeStatus = findViewById<TextView>(R.id.tvModeStatus)
        val btnToggleLayout = findViewById<Button>(R.id.btnToggleLayout)

        btnToggleLayout.setOnClickListener {
            isOverlayMode = !isOverlayMode

            if (isOverlayMode) {
                // Показуємо FrameLayout (демонстрація накладання елементів)
                demoFrameLayout.visibility = View.VISIBLE
                demoLinearLayoutContainer.visibility = View.GONE
                tvModeStatus.setText(R.string.mode_overlay)
                Toast.makeText(this, R.string.mode_overlay, Toast.LENGTH_SHORT).show()
            } else {
                // Показуємо LinearLayout (демонстрація послідовного розташування елементів)
                demoFrameLayout.visibility = View.GONE
                demoLinearLayoutContainer.visibility = View.VISIBLE
                tvModeStatus.setText(R.string.mode_sequential)
                Toast.makeText(this, R.string.mode_sequential, Toast.LENGTH_SHORT).show()
            }
        }
    }
}