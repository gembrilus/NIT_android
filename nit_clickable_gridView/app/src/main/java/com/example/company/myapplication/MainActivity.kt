package com.example.company.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.GridView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data = Array<String>(31){it.toString()}
        gridView.apply {
            adapter = ArrayAdapter<String>(this@MainActivity, android.R.layout.simple_list_item_1, data)
            columnWidth = GridView.AUTO_FIT
            setOnItemClickListener { adapterView, view, i, l ->
                (view as TextView).text = "${i / 4 + 1},${i % 4 + 1}"
            }
        }
    }
}
