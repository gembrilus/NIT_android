package com.example.company.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.reflect.Array

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                if (p0 == null) return
                val p = if (p0.matches("\\d+".toRegex())) p0.toString().toInt() else 0
                val data = MutableList(p, {(it+1).toString()})

                val adapterSpinner = ArrayAdapter<String>(this@MainActivity, android.R.layout.simple_list_item_1, data)
                adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                spinner.apply {
                    adapter = adapterSpinner
                    dropDownVerticalOffset = 100
                    dropDownHorizontalOffset = 100
                    adapterSpinner.notifyDataSetChanged()
                }

            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
        })
    }
}
