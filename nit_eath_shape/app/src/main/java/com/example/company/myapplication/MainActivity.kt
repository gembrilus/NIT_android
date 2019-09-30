package com.example.company.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.RadioButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rbs: List<RadioButton> = listOf(rbDisk, rbSphere, rbEllipsoid, rbCube)
        rbs.forEach{it.setOnCheckedChangeListener { _, _ -> tvAnswer.text = ""} }
        butAnswer.setOnClickListener { tvAnswer.text = if(rbSphere.isChecked) "Правильно!" else "Неправильно!" }
    }
}
