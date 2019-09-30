package com.example.company.myapplication

import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {

    private val REQUEST_CODE = 1
    private var IS_PERMISSION_GRANTED = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkOrRequest(android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)

        if(IS_PERMISSION_GRANTED){
            perform.setOnClickListener { result.text = numberOfFiles(path.text.toString()) }
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

    private fun numberOfFiles(path: String): String {
        if(Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED){
            val directory = File(path)
            if(directory.exists() && directory.isDirectory){
                return directory.listFiles { file -> file.isFile }.size.toString()
            }
        }
        return "Error"
    }
}
