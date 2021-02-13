package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import com.example.myapplication.ui.dashboard.DashboardFragment

class frag1 : Fragment() {

    lateinit var btnChu : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_frag1, container, false)

        btnChu = root.findViewById(R.id.button10)

        btnChu.setOnClickListener {
            activity?.let {
                val intent = Intent(activity, StoreActivity::class.java)
                startActivity(intent)
            }
        }

        return root
    }

}