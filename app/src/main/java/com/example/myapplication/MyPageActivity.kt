package com.example.myapplication

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView

class MyPageActivity : AppCompatActivity() {

    lateinit var Homebtn : ImageButton
    lateinit var Commubtn : ImageButton
    lateinit var Mypagebtn : ImageButton
    lateinit var userName: TextView
    lateinit var DBHelper: RegisterActivity.myDBHelper
    lateinit var dbswu : SQLiteDatabase
    lateinit var nickname: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)
        title = "마이페이지"
        setTitle("마이페이지")
        Homebtn = findViewById(R.id.homeBtn)
        Commubtn = findViewById(R.id.communityBtn)
        Mypagebtn = findViewById(R.id.mypageBtn)
        userName = findViewById(R.id.userName)
        DBHelper = RegisterActivity.myDBHelper(this)

        dbswu = DBHelper.readableDatabase
        var cursor : Cursor

        cursor = dbswu.rawQuery("SELECT * FROM USERS;", null)
        while (cursor.moveToNext()){
            nickname = cursor.getString(2).toString()
            break
        }

        userName.text = nickname

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