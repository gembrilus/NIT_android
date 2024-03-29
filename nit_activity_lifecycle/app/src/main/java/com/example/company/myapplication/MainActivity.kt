package com.example.company.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
            intent.putExtra("store", editText.text.toString())
        }
    }

    override fun onResume() {
        super.onResume()
        textView.text = intent.getStringExtra("store")
    }

    override fun onPause() {
        super.onPause()
        editText.setText("")
    }
}
