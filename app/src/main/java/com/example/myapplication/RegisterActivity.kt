package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    lateinit var edtId2 : EditText
    lateinit var edtPwd2 : EditText
    lateinit var edtNickname : EditText
    lateinit var btnUser2 : Button
    lateinit var dbswu : SQLiteDatabase
    lateinit var myHelper : myDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_regis)
        actionBar?.hide()
        supportActionBar?.hide()

        //회원가입 화면
        edtId2 = findViewById(R.id.edtID2)
        edtPwd2 = findViewById(R.id.edtPwd2)
        edtNickname = findViewById(R.id.edtNickname)
        btnUser2 = findViewById(R.id.btnUser2)
        myHelper = myDBHelper(this)

        btnUser2.setOnClickListener {
            dbswu = myHelper.writableDatabase
            dbswu.execSQL("INSERT INTO USERS VALUES('" + edtId2.text.toString() + "', '"
                    + edtPwd2.text.toString() + "', '" + edtNickname.text.toString() + "');")

            Toast.makeText(this, "회원가입이 되었습니다", Toast.LENGTH_SHORT).show()

            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    class myDBHelper(context: Context) : SQLiteOpenHelper(context, "SWU", null, 1) {
        override fun onCreate(db: SQLiteDatabase?) {
            db!!.execSQL("CREATE TABLE USERS ( ID CHAR(20) PRIMARY KEY, PWD CHAR(20), NICKNAME CHAR(10));")
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db!!.execSQL("DROP TABLE IF EXISTS SWU")
            onCreate(db)
        }
    }
}