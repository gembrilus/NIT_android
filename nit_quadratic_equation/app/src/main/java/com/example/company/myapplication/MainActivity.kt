package com.example.company.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toN = { el: EditText -> el.text.toString().toFloat() }

        val rootViews = listOf(x1Value, x2Value)
        val coefViews = listOf(aValue, bValue, cValue)

        val rootsCalculator = object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if(rootViews.any { it.isFocused }) return

                isSolutionExist.text = try {
                    val (a, b, c) = coefViews.map(toN)
                    val d = b*b - 4*a*c

                    when {
                        a == 0f && b == 0f && c == 0f -> {
                            rootViews.forEach { it.setText("") }

                            "Any number"
                        }
                        (a == 0f && b == 0f) || d < 0 -> {
                            rootViews.forEach { it.setText("") }

                            "No real roots"
                        }
                        a == 0f -> {
                            x1Value.setTextKeepState("${-c/b}")
                            x2Value.setText("")

                            "One root"
                        }
                        else -> {
                            val r2 = - (sqrt(d) + b) / (2*a) + 0f
                            val r1 = + (sqrt(d) - b) / (2*a) + 0f

                            x1Value.setText("$r1")
                            x2Value.setText("$r2")

                            if(r1 == r2) "One root" else "Two roots"
                        }
                    }
                } catch (e: NumberFormatException) {
                    "error"
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
        }

        val coefCalculator = object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if(coefViews.any { it.isFocused }) return

                isSolutionExist.text = try {
                    val r1 = toN(x1Value)

                    when {
                        x2Value.text.isEmpty() -> {
                            aValue.setText("0.0")
                            bValue.setText("1.0")
                            cValue.setText("${-r1}")

                            "One root"
                        }
                        else -> {
                            val r2 = toN(x2Value)

                            aValue.setText("1.0")
                            bValue.setText("${-(r1 + r2)}")
                            cValue.setText("${r1*r2}")

                            if(r1 == r2) "One root" else "Two roots"
                        }
                    }
                } catch (e: NumberFormatException) {
                    "error"
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
        }

        coefViews.forEach { it.addTextChangedListener(rootsCalculator) }
        rootViews.forEach { it.addTextChangedListener(coefCalculator) }
    }
}