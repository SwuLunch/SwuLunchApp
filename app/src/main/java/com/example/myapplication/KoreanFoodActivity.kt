package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ScrollView
import android.widget.TableLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager.widget.ViewPager
import com.example.myapplication.ui.home.HomeFragment
import com.google.android.material.tabs.TabLayout

class KoreanFoodActivity : AppCompatActivity() {

    lateinit var viewPager: ViewPager
    lateinit var tabLayout : TabLayout

    lateinit var Homebtn : ImageButton
    lateinit var Commubtn : ImageButton
    lateinit var Mypagebtn : ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.koreanfood)
        setTitle("한식")

        Homebtn = findViewById(R.id.homeBtn)
        Commubtn = findViewById(R.id.communityBtn)
        Mypagebtn = findViewById(R.id.mypageBtn)

        viewPager = findViewById(R.id.viewPager2)
        tabLayout = findViewById(R.id.tabLayout3)

        viewPager.adapter = ViewPageAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)


        // 홈버튼
        Homebtn.setOnClickListener {
            var intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        // 커뮤니티 버튼
        Commubtn.setOnClickListener {
            var intent = Intent(this, Board_Main::class.java)
            startActivity(intent)
        }

        // 마이페이지 버튼
        Mypagebtn.setOnClickListener {
            var intent = Intent(this, MyPageActivity::class.java)
            startActivity(intent)
        }
    }

}