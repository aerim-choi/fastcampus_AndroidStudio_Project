package com.hongdroid.recyclerkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rvProfile= findViewById<RecyclerView>(R.id.rv_profile)
        val profileList = arrayListOf(
            Profiles(R.drawable.man,"홍드로이드",27,"안드로이드 앱 개발자"),
            Profiles(R.drawable.woman, "안드로이드",15,"아이폰 앱 개발자"),
            Profiles(R.drawable.man, "김드로이드",10,"리액트 앱 개발자"),
            Profiles(R.drawable.woman, "신드로이드",40,"플러터 앱 개발자"),
            Profiles(R.drawable.man, "이드로이드",20,"유니티 앱 개발자"),
            Profiles(R.drawable.man,"윤드로이드",24,"알고리즘 앱 개발자"),
            Profiles(R.drawable.woman, "민드로이드",69,"웹 앱 개발자"),
            Profiles(R.drawable.man, "공드로이드",42,"하이브리드 앱 개발자"),
            Profiles(R.drawable.woman, "정드로이드",23,"그냥 앱 개발자"),
            Profiles(R.drawable.man, "고드로이드",19,"배고픈 앱 개발자"),
        )
        rvProfile.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rvProfile.setHasFixedSize(true)
        rvProfile.adapter = ProfileAdapter(profileList)
    }
}