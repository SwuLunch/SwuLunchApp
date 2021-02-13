package com.example.myapplication

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.media.Image
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class StoreActivity : AppCompatActivity()
{
    lateinit var MenuImageBtn1 : ImageButton
    lateinit var MenuImageBtn2 : ImageButton
    lateinit var reviewBtn : ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.store_main)
        title = "츄밥"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        MenuImageBtn1 = findViewById<ImageButton>(R.id.MenuImageBtn1)
        MenuImageBtn2 = findViewById<ImageButton>(R.id.MenuImageBtn2)
        reviewBtn = findViewById<ImageButton>(R.id.reviewBtn)


        MenuImageBtn1.setOnClickListener {
            var intent = Intent(this, Order_Detail::class.java)
            startActivity(intent)
        }

        reviewBtn.setOnClickListener {
            var intent = Intent(this, ReviewActivity::class.java)
            startActivity(intent)
        }

    }


}