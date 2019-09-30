package com.example.company.myapplication

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        convertButton.setOnClickListener {
            val n = number.text.toString().toIntOrNull() ?: 0
            val s = systemOfCalculus.text.toString().toIntOrNull() ?: 0
            convertResult.text = if(n > 0 && s in 2..36) n.toString(s) else "Error"
//            val res = convert(n, s)
//            convertResult.text = if (res == "") "Error" else res
        }
    }
//
//    private fun convert(n: Int, s: Int): String {
//        if (n <= 0 || s !in 2..36) return ""
//        val t = n % s
//        return convert(n / s, s) + (if (t > 9) ('a' + t - 10) else t)
//    }
//
}
