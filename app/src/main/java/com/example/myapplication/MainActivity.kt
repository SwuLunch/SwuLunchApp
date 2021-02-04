package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

class MainActivity : AppCompatActivity() {

    lateinit var edtId : EditText
    lateinit var edtPwd : EditText
    lateinit var btnLogin : Button
    lateinit var btnUser : Button
    lateinit var dbswu : SQLiteDatabase
    lateinit var myHelper : myDBHelper


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_main)

        //로그인 화면
        edtId = findViewById(R.id.edtID)
        edtPwd = findViewById(R.id.edtPwd)
        btnLogin = findViewById(R.id.btnLogin)
        btnUser = findViewById(R.id.btnUser)
        myHelper = myDBHelper(this)


        btnLogin.setOnClickListener {
            dbswu = myHelper.readableDatabase
            var cursor : Cursor
            cursor = dbswu.rawQuery("SELECT * FROM USERS;", null)

            var id = edtId.text.toString()
            var pwd = edtPwd.text.toString()
            var login = false

            while (cursor.moveToNext()) {
                if(id == cursor.getString(0)
                    && pwd == cursor.getString(1)) {
                    var intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra("nickname", cursor.getString(2).toString())
                    startActivity(intent)
                    login = true
                    break
                    }
            }

            if(login == false)
            {
                Toast.makeText(this, "회원이 아닙니다", Toast.LENGTH_SHORT).show()
            }

        }

        btnUser.setOnClickListener {
            var intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    inner class myDBHelper(context: Context) : SQLiteOpenHelper(context, "SWU", null, 1) {
        override fun onCreate(db: SQLiteDatabase?) {
            db!!.execSQL("CREATE TABLE USERS ( ID CHAR(20) PRIMARY KEY, PWD CHAR(20), NICKNAME CHAR(10));")
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db!!.execSQL("DROP TABLE IF EXISTS SWU")
            onCreate(db)
        }
    }
}
