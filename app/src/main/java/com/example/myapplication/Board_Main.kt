package com.example.myapplication


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageButton
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Board_Main : AppCompatActivity() {

    lateinit var recordRv : RecyclerView
    lateinit var dbHelper: MyDbHelper
    lateinit var addRecordBtn:FloatingActionButton
    lateinit var Homebtn : ImageButton
    lateinit var Commubtn: ImageButton
    lateinit var Mypagebtn : ImageButton
    private val NEWEST_FIRST = Constants.C_ADDED_TIME_STAMP+" DESC"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_main)
        title = "커뮤니티"
        setTitle("커뮤니티")

        dbHelper = MyDbHelper(this)
        addRecordBtn = findViewById<FloatingActionButton>(R.id.addRecordBtn)
        recordRv = findViewById<RecyclerView>(R.id.recordsRv)

        Homebtn = findViewById<ImageButton>(R.id.homeBtn)
        Mypagebtn = findViewById<ImageButton>(R.id.mypageBtn)
        Commubtn = findViewById<ImageButton>(R.id.communityBtn)


        loadRecords()

        //click FloatingActionButton to start AddUpdateRecordActivity
        addRecordBtn.setOnClickListener{
            startActivity(Intent(this, AddUpdateRecordActivity::class.java))
        }

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

    private fun loadRecords() {
        val adapterRecord = AdapterRecord(this, dbHelper.getAllRecords(NEWEST_FIRST))

        recordRv.adapter = adapterRecord
    }

    private fun searchRecords(query:String){
        val adapterRecord = AdapterRecord(this, dbHelper.searchRecords(query))
        recordRv.adapter = adapterRecord
    }

    override fun onResume() {
        super.onResume()
        loadRecords()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_board_main, menu)

        val item = menu.findItem(R.id.action_search)

        val searchView = item.actionView as androidx.appcompat.widget.SearchView

        searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText != null)
                    searchRecords(newText)
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query != null)
                    searchRecords(query)
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var id = item?.itemId
        when (id) {
            R.id.action_home -> {
                startActivity(Intent(this, HomeActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}