package com.example.myapplication

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.service.voice.VoiceInteractionSession
import android.util.Log
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

class AddReview : AppCompatActivity() {

    //permission constants
    private val CAMERA_REQUEST_CODE = 100;
    private val STORAGE_REQUEST_CODE = 100;
    private val IMAGE_PICK_CAMERA_CODE = 102;
    private val IMAGE_PICK_GALLERY_CODE = 102;
    private lateinit var cameraPermissions:Array<String>
    private lateinit var storagePermission:Array<String>

    private var actionBar:ActionBar? = null;

    lateinit var dbHelper:ReviewDBHelper
    lateinit var DBHelper: RegisterActivity.myDBHelper
    lateinit var dbswu : SQLiteDatabase

    lateinit var profileIv : ImageView
    lateinit var saveBtn: FloatingActionButton
    lateinit var contentEt : EditText
    lateinit var ratingBar: RatingBar
    lateinit var nickname : String

    //variables that will contain data to save in database
    private var imageUri: Uri? = null
    private var contextt: String? = ""
    private var ratingvalues: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_review_page)

        profileIv = findViewById<ImageView>(R.id.profileIv)
        saveBtn = findViewById<FloatingActionButton>(R.id.saveBtn)
        contentEt = findViewById<EditText>(R.id.contentEt)
        ratingBar = findViewById<RatingBar>(R.id.ratingBar)
        DBHelper = RegisterActivity.myDBHelper(this)

        dbswu = DBHelper.readableDatabase
        var cursor : Cursor

        cursor = dbswu.rawQuery("SELECT * FROM USERS;", null)
        while (cursor.moveToNext()){
            nickname = cursor.getString(2).toString()
            break
        }


        //init db helper class
        dbHelper = ReviewDBHelper(this)

        actionBar = supportActionBar
        actionBar!!.title = "리뷰 작성하기"
        //back button in actionbar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar!!.setDisplayShowHomeEnabled(true)

        //init permission arrays
        cameraPermissions = arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        storagePermission = arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)

        //click imageView to pick image
        profileIv.setOnClickListener{
            imagePickDialog();
        }
        saveBtn.setOnClickListener{
            inputData()
            var intent = Intent(this, ReviewActivity::class.java)
            startActivity(intent)
        }


    }

    private fun inputData() {
        //get data
        contextt = "" + contentEt.text.toString().trim()
        ratingvalues = ""+ ratingBar.rating.toString().trim()
        //save data to db
        val timestamp = System.currentTimeMillis()
        Log.d("camera", "image url: "+imageUri)
        val id = dbHelper.insertReview(
            ""+ nickname,
            ""+imageUri,
            ""+contextt,
            ""+ratingvalues,
            ""+timestamp
        )
        Toast.makeText(this, "Record Added against ID $id", Toast.LENGTH_SHORT).show()
    }

    private fun imagePickDialog() {
        val options = arrayOf("Camera", "Gallery")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Pick Image From")
        builder.setItems(options){dialog, which ->
            if(which ==0){
                Log.d("camera", "which 0  in")
                //camera pick
                if(!checkCameraPermission()){
                    requestCameraPermission()
                }else{
                    pickFromCamera()
                }

            }else{
                //gallery pick
                Log.d("camera", "which else 0  in")
                if(!checkStoragePermission()){
                    requestStoragePermission()
                }else{
                    pickFromGallery()
                }
            }
        }
        //show dialog
        builder.show()
    }

    private fun pickFromGallery() {
        //pick gallery using Intent
        Log.d("camera", "pickfromgallery in")
        val galleryIntent = Intent(Intent.ACTION_PICK)
        galleryIntent.type = "image/*"  //only image pick
        startActivityForResult(
            galleryIntent,
            IMAGE_PICK_GALLERY_CODE
        )
    }

    private fun requestStoragePermission() {
        ActivityCompat.requestPermissions(this, storagePermission, STORAGE_REQUEST_CODE)
    }

    private fun checkStoragePermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun pickFromCamera() {
        //pick image from camera using Intent
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "Image Title")
        values.put(MediaStore.Images.Media.DESCRIPTION, "Image Desription")
        Log.d("camera", "pickfromcamera in")
        //put image uri
        imageUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        //intent to open camera
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        startActivityForResult(
            cameraIntent,
            IMAGE_PICK_CAMERA_CODE
        )
    }

    private fun requestCameraPermission() {
        ActivityCompat.requestPermissions(this, cameraPermissions, CAMERA_REQUEST_CODE)
    }

    private fun checkCameraPermission(): Boolean {
        val results = ContextCompat.checkSelfPermission(this,
            android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
        val results1 = ContextCompat.checkSelfPermission(this,
            android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED

        return results && results1
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            CAMERA_REQUEST_CODE -> {
                if(grantResults.isNotEmpty()){
                    //if allowed returns true otherwise false
                    val cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED
                    val storageAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED
                    if(cameraAccepted && storageAccepted)
                        pickFromCamera()
                    else
                        Toast.makeText(this,"Camera and Storage permission are required", Toast.LENGTH_SHORT).show()
                }

            }
            STORAGE_REQUEST_CODE -> {
                if(grantResults.isNotEmpty()){
                    val storageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED
                    if(storageAccepted)
                        pickFromGallery()
                }else{
                    Toast.makeText(this,"Storage permission is required", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //image picked from camera or gallery will be received here
        if(resultCode == Activity.RESULT_OK){
            //image is picked
            if(requestCode == IMAGE_PICK_GALLERY_CODE){
                CropImage.activity(data!!.data).setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1,1)
                    .start(this)
            }else if(requestCode == IMAGE_PICK_CAMERA_CODE){
                CropImage.activity(imageUri).setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1,1)
                    .start(this)
            }else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
                val result = CropImage.getActivityResult(data)
                if(resultCode == Activity.RESULT_OK){
                    val resultUri = result.uri
                    imageUri = resultUri
                    //set image
                    profileIv.setImageURI(resultUri)
                }else if(resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
                    //error
                    val error = result.error
                    Toast.makeText(this, ""+error, Toast.LENGTH_SHORT).show()
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}