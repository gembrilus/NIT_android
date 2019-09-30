package com.example.company.myapplication

import android.app.Activity
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*


class MainActivity : AppCompatActivity() {

    private val REQUEST_CODE = 1
    private var IS_PERMISSION_GRANTED = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkOrRequest(android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (IS_PERMISSION_GRANTED) {
            perform.setOnClickListener {
                if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
                    val path = Environment.getExternalStorageDirectory()
                    val file1 = File(path, filepath.text.toString())
                    val file2 = File(path, filepath2.text.toString())
                    if(!file1.exists()){
                        filepath.setText("error")
                        return@setOnClickListener
                    }
                    if(!file2.exists()){
                        filepath2.setText("error")
                        return@setOnClickListener
                    }
                    val fileTemorary = File(path, ".temp" + Date().time)
                    file1 writeTo fileTemorary
                    file2 writeTo file1
                    fileTemorary writeTo file2
                    fileTemorary.delete()
                }
            }
        }
    }

    private fun checkOrRequest(vararg perms: String) {
        var list: Array<String> = arrayOf()
        for (p in perms) {
            if (ContextCompat.checkSelfPermission(this, p) != PackageManager.PERMISSION_GRANTED) {
                list += p
            }
        }
        if (list.size == 0) IS_PERMISSION_GRANTED = true
        else ActivityCompat.requestPermissions(this, list, REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                IS_PERMISSION_GRANTED = true;
            }
        }
    }

    private infix fun File.writeTo(file: File?) {
        var reader: BufferedReader? = null
        var writer: BufferedWriter? = null
        try {
            reader = BufferedReader(FileReader(this))
            writer = BufferedWriter(FileWriter(file))
            while (reader.ready()) {
                writer.write(reader.readLine())
            }
        } catch (e: FileNotFoundException){
            e.printStackTrace()
        } catch (e: IOException){
            e.printStackTrace()
        } finally {
            reader?.close()
            writer?.close()
        }
    }
}
