package com.example.company.myapplication

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun checkBtnClick(v: View) = try{
        val inputNumber = editText.text.toString().toInt()
        textView.text = if(isPrime(inputNumber)) "prime" else "not prime"
    } catch (e: Exception){
        with(textView) {
            setTextColor(Color.RED)
            text = "error"
        }
    }

    private fun isPrime(n: Int): Boolean {
        if (n <= 1) return false
        var d = 2
        while (d * d <= n) {
            if (n % d == 0) return false
            d++
        }
        return true
    }
}
