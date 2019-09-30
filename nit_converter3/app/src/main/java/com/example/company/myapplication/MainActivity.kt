package com.example.company.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var onChanged1 = false
        var onChanged2 = false
        val CONV = 39370f
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                onChanged1 = false
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                onChanged1 = true
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!onChanged2) {
                    if (!p0.isNullOrEmpty() && p0.matches("\\d+".toRegex())) {
                        status.text = ""
                        val res = (p0.toString().toFloat() / CONV).toString()
                        editText2.setText(res)
                    } else status.text = "error"
                }
            }
        })

        editText2.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                onChanged2 = false
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                onChanged2 = true
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!onChanged1) {
                    if (!p0.isNullOrEmpty() && p0.matches("\\d+".toRegex())) {
                        status.text = ""
                        val res = (p0.toString().toFloat() * CONV).toString()
                        editText.setText(res)
                    } else status.text = "error"
                }
            }

        })

    }
}
