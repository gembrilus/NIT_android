package com.example.company.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.SeekBar
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        seekBar.setOnSeekBarChangeListener(SeekBarListener())
    }

    private inner class SeekBarListener : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) = Unit
        override fun onStartTrackingTouch(p0: SeekBar?) = Unit
        override fun onStopTrackingTouch(p0: SeekBar?) {
            if(p0 == null) return
            val list = MutableList(p0.progress){(it * it).toString()}
            val list_adapter = ArrayAdapter<String>(this@MainActivity, android.R.layout.simple_list_item_1, list)
            listView.adapter = list_adapter
            list_adapter.notifyDataSetChanged()
        }
    }
}
