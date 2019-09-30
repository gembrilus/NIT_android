package com.example.company.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data = MutableList(11) {(it * it).toString()}
        val adapter_list = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data)

        listView.apply {
            adapter = adapter_list
            setOnItemClickListener { adapterView, view, i, l ->
                val item = adapterView.getItemAtPosition(i) as String
                data.remove(item)
                adapter_list.notifyDataSetChanged()
            }
        }
    }
}
