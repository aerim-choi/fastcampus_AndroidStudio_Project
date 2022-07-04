package com.example.aoppart2chapter3

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.NumberPicker
import androidx.appcompat.widget.AppCompatButton
import kotlin.math.min

class MainActivity : AppCompatActivity() {

    private val numberPicker1 : NumberPicker by lazy{
        findViewById<NumberPicker>(R.id.numberPicker1)
            .apply {
                minValue=0
                maxValue=9
            }
    }
    private val numberPicker2 : NumberPicker by lazy{
        findViewById<NumberPicker>(R.id.numberPicker2)
            .apply {
                minValue=0
                maxValue=9
            }
    }
    private val numberPicker3 : NumberPicker by lazy{
        findViewById<NumberPicker>(R.id.numberPicker3)
            .apply {
                minValue=0
                maxValue=9
            }
    }

    private val openButton : AppCompatButton by lazy{
        findViewById<AppCompatButton>(R.id.openButton)
    }

    private val changePasswordButton : AppCompatButton by lazy{
        findViewById<AppCompatButton>(R.id.changePasswordButtom)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numberPicker1
        numberPicker2
        numberPicker3

        openButton.setOnClickListener{
            getSharedPreferences("password", Context.MODE_PRIVATE)

        }

    }
}