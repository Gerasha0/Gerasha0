package com.example.lab4

import android.os.Bundle
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.constraintlayout.widget.Group
import androidx.core.view.isVisible
import com.google.android.material.card.MaterialCardView
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private lateinit var contentContainer: FrameLayout
    private lateinit var tabLayout: TabLayout

    // State for Task 2
    private var isMovableMoved = false

    // State for Task 4
    private var currentChainStyleIndex = 0
    private val chainStyles = intArrayOf(
        ConstraintSet.CHAIN_SPREAD,
        ConstraintSet.CHAIN_SPREAD_INSIDE,
        ConstraintSet.CHAIN_PACKED
    )
    private val chainStyleNames = arrayOf("SPREAD", "SPREAD_INSIDE", "PACKED")

    private var currentCircleAngle = 135f
    private var isAnimSetStateB = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        contentContainer = findViewById(R.id.contentContainer)
        tabLayout = findViewById(R.id.tabLayout)

        setupTabs()
        showTaskView(0)
    }

    private fun setupTabs() {
        val tabTitles = arrayOf(
            getString(R.string.tab_task1),
            getString(R.string.tab_task2),
            getString(R.string.tab_task3),
            getString(R.string.tab_task4)
        )

        for (title in tabTitles) {
            tabLayout.addTab(tabLayout.newTab().setText(title))
        }

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                showTaskView(tab.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    private fun showTaskView(position: Int) {
        contentContainer.removeAllViews()
        val inflater = LayoutInflater.from(this)

        when (position) {
            0 -> setupTask1(inflater)
            1 -> setupTask2(inflater)
            2 -> setupTask3(inflater)
            3 -> setupTask4(inflater)
        }
    }

    // TASK 1
    private fun setupTask1(inflater: LayoutInflater) {
        val view = inflater.inflate(R.layout.layout_task1_relative, contentContainer, false)
        contentContainer.addView(view)

        val btnLeft = view.findViewById<Button>(R.id.btnTask1Left)
        val btnRight = view.findViewById<Button>(R.id.btnTask1Right)

        btnLeft.setOnClickListener {
            Toast.makeText(this, "Кнопка ліворуч (RelativeLayout)", Toast.LENGTH_SHORT).show()
        }
        btnRight.setOnClickListener {
            Toast.makeText(this, "Кнопка праворуч (RelativeLayout)", Toast.LENGTH_SHORT).show()
        }
    }

    // TASK 2
    private fun setupTask2(inflater: LayoutInflater) {
        isMovableMoved = false
        val view = inflater.inflate(R.layout.layout_task2_dynamic, contentContainer, false)
        contentContainer.addView(view)

        val relativeContainer = view.findViewById<RelativeLayout>(R.id.relativeContainerTask2)
        val cardMovable = view.findViewById<MaterialCardView>(R.id.cardMovable)
        val btnMove = view.findViewById<Button>(R.id.btnMoveComponent)
        val btnReset = view.findViewById<Button>(R.id.btnResetComponent)
        val tvStatus = view.findViewById<TextView>(R.id.tvTask2Status)

        btnMove.setOnClickListener {
            if (!isMovableMoved) {
                // Remove view from parent RelativeLayout
                relativeContainer.removeView(cardMovable)

                // Create new layout params to position card below Bottom Anchor
                val params = RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                params.addRule(RelativeLayout.BELOW, R.id.tvBottomAnchor)
                val marginPx = (16 * resources.displayMetrics.density).toInt()
                params.setMargins(0, marginPx, 0, marginPx)

                // Re-add view with new layout parameters
                relativeContainer.addView(cardMovable, params)

                isMovableMoved = true
                tvStatus.setText(R.string.task2_status_moved)
            }
        }

        btnReset.setOnClickListener {
            if (isMovableMoved) {
                // Remove view from parent RelativeLayout
                relativeContainer.removeView(cardMovable)

                // Create layout params to restore card position below Top Anchor
                val params = RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                params.addRule(RelativeLayout.BELOW, R.id.tvTopAnchor)
                val marginPx = (16 * resources.displayMetrics.density).toInt()
                params.setMargins(0, marginPx, 0, marginPx)

                // Re-add view with original layout parameters
                relativeContainer.addView(cardMovable, params)

                isMovableMoved = false
                tvStatus.setText(R.string.task2_status_initial)
            }
        }
    }

    // TASK 3
    private fun setupTask3(inflater: LayoutInflater) {
        val view = inflater.inflate(R.layout.layout_task3_constraint, contentContainer, false)
        contentContainer.addView(view)

        val btnLeft = view.findViewById<Button>(R.id.btnTask3Left)
        val btnRight = view.findViewById<Button>(R.id.btnTask3Right)

        btnLeft.setOnClickListener {
            Toast.makeText(this, "Кнопка ліворуч (ConstraintLayout)", Toast.LENGTH_SHORT).show()
        }
        btnRight.setOnClickListener {
            Toast.makeText(this, "Кнопка праворуч (ConstraintLayout)", Toast.LENGTH_SHORT).show()
        }
    }

    // TASK 4
    private fun setupTask4(inflater: LayoutInflater) {
        val view = inflater.inflate(R.layout.layout_task4_advanced, contentContainer, false)
        contentContainer.addView(view)

        val rootLayout = view.findViewById<ConstraintLayout>(R.id.task4RootContainer)
        val btnToggleChainStyle = view.findViewById<Button>(R.id.btnToggleChainStyle)
        val demoGroup = view.findViewById<Group>(R.id.demoGroup)
        val btnToggleGroup = view.findViewById<Button>(R.id.btnToggleGroup)
        val tvCircleOrbit = view.findViewById<TextView>(R.id.tvCircleOrbit)
        val btnRotateCircle = view.findViewById<Button>(R.id.btnRotateCircle)
        val animContainer = view.findViewById<ConstraintLayout>(R.id.animContainer)
        val btnAnimateConstraintSet = view.findViewById<Button>(R.id.btnAnimateConstraintSet)

        // 1. Chains Style Toggle using ConstraintSet
        btnToggleChainStyle.setOnClickListener {
            currentChainStyleIndex = (currentChainStyleIndex + 1) % chainStyles.size
            val newStyle = chainStyles[currentChainStyleIndex]

            TransitionManager.beginDelayedTransition(rootLayout)
            val constraintSet = ConstraintSet()
            constraintSet.clone(rootLayout)
            constraintSet.setHorizontalChainStyle(R.id.btnChain1, newStyle)
            constraintSet.applyTo(rootLayout)

            btnToggleChainStyle.text = "Стиль ланцюжка: ${chainStyleNames[currentChainStyleIndex]}"
        }

        // 2. Group Visibility Toggle
        btnToggleGroup.setOnClickListener {
            val visible = demoGroup.isVisible
            demoGroup.isVisible = !visible
            btnToggleGroup.text = if (!visible) "Сховати Групу" else "Показати Групу"
        }

        // 3. constraintCircle Rotation
        btnRotateCircle.setOnClickListener {
            currentCircleAngle = (currentCircleAngle + 30f) % 360f
            val params = tvCircleOrbit.layoutParams as ConstraintLayout.LayoutParams
            params.circleAngle = currentCircleAngle
            tvCircleOrbit.layoutParams = params
            btnRotateCircle.text = "Обертати по колу (Кут: ${currentCircleAngle.toInt()}°)"
        }

        // 4. ConstraintSet Animation in Code
        btnAnimateConstraintSet.setOnClickListener {
            TransitionManager.beginDelayedTransition(animContainer)
            val constraintSet = ConstraintSet()
            constraintSet.clone(animContainer)

            if (!isAnimSetStateB) {
                // Move Box A to End and Box B to Start
                constraintSet.clear(R.id.boxA, ConstraintSet.START)
                constraintSet.connect(R.id.boxA, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)

                constraintSet.clear(R.id.boxB, ConstraintSet.END)
                constraintSet.connect(R.id.boxB, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
            } else {
                // Restore Box A to Start and Box B to End
                constraintSet.clear(R.id.boxA, ConstraintSet.END)
                constraintSet.connect(R.id.boxA, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)

                constraintSet.clear(R.id.boxB, ConstraintSet.START)
                constraintSet.connect(R.id.boxB, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)
            }

            constraintSet.applyTo(animContainer)
            isAnimSetStateB = !isAnimSetStateB
        }
    }
}
