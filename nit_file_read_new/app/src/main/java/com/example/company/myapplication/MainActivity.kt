package com.example.company.myapplication

import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException


class MainActivity : AppCompatActivity() {

    private val REQUEST_CODE = 1
    private var IS_PERMISSION_GRANTED: Boolean = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkOrRequest(android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)

        if(IS_PERMISSION_GRANTED){
            perform.setOnClickListener { readFile(filepath.text.toString()) }
        }
    }

    private fun checkOrRequest(vararg permissions: String) {
        var list: Array<String> = arrayOf()
        for (p in permissions) {
            if (ContextCompat.checkSelfPermission(this, p) != PackageManager.PERMISSION_GRANTED) {
                list += p
            }
        }
        if (list.size == 0) {
            IS_PERMISSION_GRANTED = true
        } else
            ActivityCompat.requestPermissions(this, list, REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                IS_PERMISSION_GRANTED = true
            }
        }
    }

    private fun readFile(filename: String) {
        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            val path = Environment.getExternalStorageDirectory()
            val result = findViewById<TextView>(R.id.result)
            var reader: BufferedReader? = null
            try {
                val file = File(path, filename)
                reader = BufferedReader(FileReader(file))
                result.setText(reader.readText())
            } catch (e: IOException){
            } finally {
                reader?.close()
            }
        }
    }
}
