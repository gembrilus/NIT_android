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

        val data = MutableList<String>(31){it.toString()}
        val gvAdapter = ArrayAdapter<String>(this@MainActivity, android.R.layout.simple_list_item_1, data)
        gridView.apply {
            adapter = gvAdapter
            columnWidth = GridView.AUTO_FIT
            setOnItemClickListener { _, _, i, _ ->
                data.removeAt(i)
                gvAdapter.notifyDataSetChanged()
            }
        }
    }
}
