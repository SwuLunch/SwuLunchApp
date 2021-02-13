package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.*

class HomeActivity : AppCompatActivity() {

    lateinit var btnHan : ImageButton
    lateinit var Homebtn : ImageButton
    lateinit var Commubtn : ImageButton
    lateinit var Mypagebtn : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // 상단바 없애기
        supportActionBar?.hide()
        //setTitle("한식양식중식일식")

        btnHan = findViewById(R.id.Hansikbtn)
        Homebtn = findViewById(R.id.homeBtn)
        Commubtn = findViewById(R.id.communityBtn)
        Mypagebtn = findViewById(R.id.mypageBtn)


        // 한식 버튼
        btnHan.setOnClickListener {
            var intent = Intent(this, KoreanFoodActivity::class.java)
            startActivity(intent)
        }

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