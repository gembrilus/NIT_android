package com.example.company.myapplication

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data = MutableList(11) { (it * it).toString() }
        val list_adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, data)
        listView.apply {
            adapter = list_adapter
            setOnItemClickListener { parent, _, position, _ ->
                val item = parent.getItemAtPosition(position) as String
                data.add(item)
                list_adapter.notifyDataSetChanged()
            }
        }
    }
}
