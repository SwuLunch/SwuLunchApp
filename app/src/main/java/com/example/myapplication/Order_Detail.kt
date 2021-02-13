package com.example.myapplication

import android.app.Dialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.media.Image
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Telephony
import android.telephony.SmsManager
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.app.ActivityCompat

class Order_Detail : AppCompatActivity(), OrderDialogInterface {

    lateinit var orderBtn : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_detail)
        title = "츄밥"

        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.RECEIVE_SMS)
            != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.RECEIVE_SMS, android.Manifest.permission.SEND_SMS), 111)
        }else
            receiveMsg()


        var sendtext = "주문이 들어왔습니다."




        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        orderBtn = findViewById<ImageButton>(R.id.orderBtn)

        orderBtn.setOnClickListener {
            var sms = SmsManager.getDefault()
            val orderDialog = OrderDialog(this, this)
            orderDialog.show()
            sms.sendTextMessage("486162215","ME",sendtext, null,null)
            //var intent = Intent(this, OrderSucces::class.java)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 111 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            receiveMsg()

        }

    }

    private fun receiveMsg(){
        var br = object : BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
                    for(sms in Telephony.Sms.Intents.getMessagesFromIntent(intent)){
                        Toast.makeText(applicationContext, sms.displayMessageBody, Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }
        registerReceiver(br, IntentFilter("android.provider.Telephony.SMS_RECEIVED"))
    }

    override fun onOrderBtnClick() {
        var intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}