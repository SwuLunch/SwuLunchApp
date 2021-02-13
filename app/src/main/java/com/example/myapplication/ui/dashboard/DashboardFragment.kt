package com.example.myapplication.ui.dashboard

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.*
import com.google.android.material.floatingactionbutton.FloatingActionButton


class DashboardFragment : Fragment() {

    lateinit var recordRv : RecyclerView
    lateinit var dbHelper: ReviewDBHelper
    lateinit var addReviewBtn: FloatingActionButton
    private val NEWEST_FIRST = Constants_Review.R_ADDED_TIME_STAMP+" DESC"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


/*
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        val intent = Intent(getActivity(), Board_Main::class.java)
        startActivity(intent)

        return inflater.inflate(R.layout.activity_board_main, container, false)


    }*/


}