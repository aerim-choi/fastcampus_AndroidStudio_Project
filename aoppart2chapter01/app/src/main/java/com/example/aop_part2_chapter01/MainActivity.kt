package com.example.aop_part2_chapter01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val heightEditText :EditText = findViewById(R.id.heightEditText) //방법 1 (명시적 선언)
        val weightEditText = findViewById<EditText>(R.id.weightEditText)  //방법 2 (추론적으로 변수에 할당)

        val resultButton = findViewById<Button>(R.id.resultButton)

        resultButton.setOnClickListener{
            Log.d("MainActivity","ResultButton이 클릭이 되었습니다.")

            if(heightEditText.text.isEmpty()||weightEditText.text.isEmpty()){
                Toast.makeText(this,"빈 값이 있습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener //setOnClickListener함수를 나갈거임
            }

            //이 아래로는 절대 빈 값이 올 수 없음

            val height : Int = heightEditText.text.toString().toInt()
            val weight : Int =weightEditText.text.toString().toInt()


            Log.d("MainAcitvity","height $height weight $weight")

            val intent = Intent(this, ResultActivity::class.java) //현재액티비티,다음 액티비티를 매게변수로 넘겨준다.

            //ResultActivity를 통해 키와 몸무게 값 가져오기
            intent.putExtra("height",height)
            intent.putExtra("weight",weight)

            startActivity(intent)
        }
    }
}