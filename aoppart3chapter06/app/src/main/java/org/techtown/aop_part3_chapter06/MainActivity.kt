package org.techtown.aop_part3_chapter06

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.techtown.aop_part3_chapter06.chatlist.ChatListFragment
import org.techtown.aop_part3_chapter06.home.HomeFragment
import org.techtown.aop_part3_chapter06.mypage.MyPageFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFragment = HomeFragment()
        val chatListFragment = ChatListFragment()
        val myPageFragment = MyPageFragment()

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        replaceFragment(homeFragment)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home ->replaceFragment(homeFragment)
                R.id.chatList->replaceFragment(chatListFragment)
                R.id.myPage->replaceFragment(myPageFragment)
            }
            true
        }
    }

    private fun replaceFragment(fragment : Fragment){
        supportFragmentManager.beginTransaction()
            .apply{
                replace(R.id.fragmentContainer,fragment)
                commit()
            }
    }

}