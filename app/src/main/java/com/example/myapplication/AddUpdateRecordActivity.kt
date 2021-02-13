package com.example.myapplication

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.service.voice.VoiceInteractionSession
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.blogspot.atifsoftwares.circularimageview.CircularImageView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import java.util.jar.Manifest

class AddUpdateRecordActivity : AppCompatActivity() {

    //permission constants
    private val CAMERA_REQUEST_CODE = 100;
    private val STORAGE_REQUEST_CODE = 100;
    private val IMAGE_PICK_CAMERA_CODE = 102;
    private val IMAGE_PICK_GALLERY_CODE = 102;
    private lateinit var cameraPermissions:Array<String>
    private lateinit var storagePermission:Array<String>

    private var actionBar:ActionBar? = null;

    lateinit var dbHelper:MyDbHelper

    //lateinit var profileIv : CircularImageView
    lateinit var saveBtn: FloatingActionButton
    lateinit var nameEt : EditText
    lateinit var whereEt : Spinner
    lateinit var timeEt : EditText
    lateinit var contentEt : EditText


    //variables that will contain data to save in database

    private var name:String? = ""
    private var nickname: String = ""
    private var where:String? = ""
    private var time:String? = ""
    private var contextt: String? = ""
    private var spinnerresult: String? = ""
    var list_of_items = arrayOf("장소를 선택하세요","50주년관", "누리관", "인문사회관", "제1과학관","제2과학관","기숙사","교외")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_update_record)

        saveBtn = findViewById<FloatingActionButton>(R.id.saveBtn)
        nameEt = findViewById<EditText>(R.id.nameEt)
        whereEt = findViewById<Spinner>(R.id.whereEt)
        timeEt = findViewById<EditText>(R.id.timeEt)
        contentEt = findViewById<EditText>(R.id.contentEt)

        whereEt.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, list_of_items )


        //init db helper class
        dbHelper = MyDbHelper(this)

        actionBar = supportActionBar
        actionBar!!.title = "게시물 작성하기"
        //back button in actionbar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar!!.setDisplayShowHomeEnabled(true)

        whereEt.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                spinnerresult = list_of_items[position]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                println("만날 장소를 선택하시오")
            }
        }


        //click imageView to pick image
        saveBtn.setOnClickListener{
            inputData()
            var intent = Intent(this, Board_Main::class.java)
            startActivity(intent)
        }
    }



    private fun inputData() {
        //get data
        nickname = ""
        name = ""+nameEt.text.toString().trim()
        where = spinnerresult
        time = "" + timeEt.text.toString().trim()
        contextt = "" + contentEt.text.toString().trim()

        //save data to db
        val timestamp = System.currentTimeMillis()
        val id = dbHelper.insertRecord(
            ""+name,
            ""+time,
            ""+where,
            ""+contextt,
            ""+timestamp,
            ""+timestamp
        )
        Toast.makeText(this, "Record Added against ID $id", Toast.LENGTH_SHORT).show()
    }

}