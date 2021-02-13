package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ReviewActivity : AppCompatActivity() {

    lateinit var recordRv : RecyclerView
    lateinit var dbHelper: ReviewDBHelper
    lateinit var addReviewBtn: FloatingActionButton
    private val NEWEST_FIRST = Constants_Review.R_ADDED_TIME_STAMP+" DESC"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)
        title = "츄밥"
        setTitle("츄밥")

        dbHelper = ReviewDBHelper(this)
        addReviewBtn = findViewById<FloatingActionButton>(R.id.addReviewBtn)
        recordRv = findViewById<RecyclerView>(R.id.recordsRv)

        loadRecords()

        //click FloatingActionButton to start AddUpdateRecordActivity
        addReviewBtn.setOnClickListener{
            startActivity(Intent(this, AddReview::class.java))
        }

    }

    private fun loadRecords() {
        val adapterReview = AdapterReview(this, dbHelper.getAllRecords(NEWEST_FIRST))

        recordRv.adapter = adapterReview
    }

}