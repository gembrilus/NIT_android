package com.example.company.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Math.abs

class MainActivity : AppCompatActivity() {

    private lateinit var spinners: List<Spinner>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        val adapter = ArrayAdapter.createFromResource(this, R.array.x0, android.R.layout.simple_spinner_item)
        spinners.forEach {
            it.adapter = adapter
            it.onItemSelectedListener = selectedListener
        }

    }

    private fun init(){
        spinners = listOf(
                spinner11, spinner12, spinner13,
                spinner21, spinner22, spinner23,
                spinner31, spinner32, spinner33)
    }

    private val selectedListener = object : AdapterView.OnItemSelectedListener{

        override fun onNothingSelected(p0: AdapterView<*>?) = Unit

        override fun onItemSelected(parent: AdapterView<*>?, spinner: View?, position: Int, id: Long) {
            var rows = arrayOf(0, 0, 0)
            var columns = arrayOf(0, 0, 0)
            var d1 = 0
            var d2 = 0
            var count0 = 0
            var countX = 0
                    cycle@for((i, s) in spinners.withIndex()){
                        val item = s.selectedItem.toString()
                        val p = i % 3
                        val h = i / 3
                        when(item){
                            "0" -> {
                                count0++
                                columns[p]++
                                if(i % 4 == 0) d1++
                                if(i != 0 && i != 8 && i % 2 == 0) d2++
                                rows[h]++
                            }
                            "X" -> {
                                countX++
                                columns[p]--
                                if(i % 4 == 0) d1--
                                if(i != 0 && i != 8 && i % 2 == 0) d2--
                                rows[h]--
                            }
                            "" -> continue@cycle
                        }
                        if(rows[h] == 3 || columns[p] == 3 || d1 == 3 || d2 == 3) {
                            status.text = "0 won"
                            break
                        }
                        else if(rows[h] == -3 || columns[p] == -3 || d1 == -3 || d2 == -3){
                            status.text = "X won"
                            break
                        }
                        else if(abs(count0-countX) > 1) status.text = "Invalid"
                        else if (count0+countX == 9) status.text = "Draw"
                        else status.text = ""
                    }
        }

    }
}
