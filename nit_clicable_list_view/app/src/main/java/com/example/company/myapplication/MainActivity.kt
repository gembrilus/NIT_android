package com.example.company.myapplication

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = MutableList(5, {x -> if(x == 0) 1 else x+1})
        val adapter = ArrayAdapter<Int>(this, android.R.layout.simple_list_item_1, list)
        listView1.adapter = adapter

        listView1.setOnItemClickListener { adapterView, view, i, l ->
            val intent = Intent(this, ListItemActivity::class.java)
            intent.putExtra("text", "${i+1}")
            startActivity(intent)
        }
    }
}
