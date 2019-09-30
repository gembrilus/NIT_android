package com.example.company.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        add.setOnClickListener { answer.text = calc(sum) }
        subtr.setOnClickListener { answer.text = calc(subtract) }
        mul.setOnClickListener { answer.text = calc(multiply) }
        divide.setOnClickListener { answer.text = calc(div) }
    }

    private fun getArg(v: EditText): Int? {
        return v.text.toString().toIntOrNull()

    }

    private fun calc(func: (Int, Int) -> String): String {
        val a = getArg(arg1)
        val b = getArg(arg2)
        if (a == null || b == null) return "Input Error"
        else return func(a, b)
    }

    private val sum = { x: Int, y: Int -> (x + y).toString() }
    private val multiply = { x: Int, y: Int -> (x * y).toString() }
    private val subtract = { x: Int, y: Int -> (x - y).toString() }
    private val div = { x: Int, y: Int -> if (y != 0) (x / y).toString() else "Div by zero" }
}
