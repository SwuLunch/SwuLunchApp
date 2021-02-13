package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class OrderSucces : AppCompatActivity() {
    lateinit var Homebtn : ImageButton
    lateinit var Commubtn : ImageButton
    lateinit var Mypagebtn : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_succes)
        title = "츄밥"

        
        Homebtn = findViewById(R.id.homeBtn)
        Commubtn = findViewById(R.id.communityBtn)
        Mypagebtn = findViewById(R.id.mypageBtn)

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