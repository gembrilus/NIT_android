package com.example.company.myeditor

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var savedText: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                checkAndShowStatus()
                stats_view.text = countWords(p0)
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
        })

        save_button.setOnClickListener {
            savedText = editText.text.toString()
            checkAndShowStatus()
        }
        load_button.setOnClickListener {
            editText.setText(savedText)
            checkAndShowStatus()
        }
        clear_button.setOnClickListener {
            editText.setText("")
            checkAndShowStatus()
        }
    }

    private fun countWords(e: Editable?) = e?.split("\\b[\\W\\s\\d]+\\b".toRegex())?.count().toString()
    private fun checkAndShowStatus() {
        if(savedText == editText.text.toString())
            unsaved_changes_view.text = "All changes saved"
        else unsaved_changes_view.text = "Unsaved changes"
    }
}
