package com.example.aop_part2_chapter02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {

    private val clearButton: Button by lazy{
        findViewById<Button>(R.id.clearButton)
    }

    private val addButton:Button  by lazy{
        findViewById<Button>(R.id.addButton)
    }

    private val runButton:Button by lazy{
        findViewById<Button>(R.id.runButton)
    }
    private val numberPicker:NumberPicker by lazy{
        findViewById<NumberPicker>(R.id.numberPicker)
    }
    private val numberTextViewList:List<TextView> by lazy {
        listOf<TextView>(
            findViewById<TextView>(R.id.textView1),
            findViewById<TextView>(R.id.textView2),
            findViewById<TextView>(R.id.textView3),
            findViewById<TextView>(R.id.textView4),
            findViewById<TextView>(R.id.textView5),
            findViewById<TextView>(R.id.textView6),
        )
    }
    private var didRun = false
    private val pickNumberSet = hashSetOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numberPicker.minValue = 1
        numberPicker.maxValue = 45

        initRunButton()
        initAddButton()
        initClearButton()
    }
    private fun initRunButton(){
        runButton.setOnClickListener{
            val list = getRandomNumber()

            list.forEachIndexed{ index, number->
                val textView =numberTextViewList[index]
                textView.text= number.toString()
                textView.isVisible = true
                setNumberBackground(number,textView)
            }
            didRun=true
        }
    }
    private fun initAddButton(){
        addButton.setOnClickListener{
            if(didRun){ //이미 자동생성 시작을 눌렀을 경우
                Toast.makeText(this,"초기화 후에 시도해주세요 ",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(pickNumberSet.size>=5){
                Toast.makeText(this,"번호는 5개까지만 선택할 수 있습니다. ",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(pickNumberSet.contains(numberPicker.value)){
                Toast.makeText(this,"이미 선택한 번호입니다. ",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val textView =numberTextViewList[pickNumberSet.size]
            textView.isVisible = true
            textView.text=numberPicker.value.toString()

            setNumberBackground(numberPicker.value,textView)
            pickNumberSet.add(numberPicker.value)
        }
    }
    //번호 backgroud 설정함수
    private fun setNumberBackground(number:Int,textView:TextView){
        when(number){
            in 1..10->textView.background = ContextCompat.getDrawable(this,R.drawable.circle_yellow)
            in 11..20->textView.background = ContextCompat.getDrawable(this,R.drawable.circle_blue)
            in 21..30->textView.background = ContextCompat.getDrawable(this,R.drawable.circle_red)
            in 31..40->textView.background = ContextCompat.getDrawable(this,R.drawable.circle_gray)
            in 41..45->textView.background = ContextCompat.getDrawable(this,R.drawable.circle_green)
        }
    }

    private fun initClearButton(){
        clearButton.setOnClickListener {
            pickNumberSet.clear()
            numberTextViewList.forEach{
                it.isVisible=false

            }
            didRun=false
        }
    }
    //랜덤 넘버 생성
    private fun getRandomNumber():List<Int>{
        val numberList = mutableListOf<Int>().apply{
            for(i in 1..45) {
                if(pickNumberSet.contains(i)) {
                    continue //번호 중복 방지
                }
                this.add(i) //1~45까지 리스트에 초기화
            }
        }
        numberList.shuffle()

        val newList = pickNumberSet.toList() + numberList.subList(0,6-pickNumberSet.size)
                        //이미 생성된 번호 리스트 + 자동생성이 된 리스트
        return newList.sorted() //6개의 랜덤된 값을 오름차순으로 정렬
    }
}