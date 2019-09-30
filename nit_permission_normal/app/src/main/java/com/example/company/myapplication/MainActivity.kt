package com.example.company.myapplication

import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            textView.text = Request().execute(editText.text.toString()).get()
        }
    }

    class Request : AsyncTask<String, Void, String>() {
        override fun doInBackground(vararg params: String?): String {
            return try {
                with(URL(params[0]).openConnection() as HttpURLConnection) {
                    when(responseCode) {
                        404, 500 -> "Failed"
                        else -> "Ok"
                    }
                }
            } catch (e: MalformedURLException) {
                "Failed"
            } catch (e: IOException) {
                "Failed"
            }
        }
    }
}
