package com.example.myapplication

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.ImageButton

class OrderDialog(context: Context, OrderDialogInterface: OrderDialogInterface) : Dialog(context), View.OnClickListener{

    val TAG: String = "로그"
    private var orderDialogInterface: OrderDialogInterface ?= null
    lateinit var orderpopBtn: ImageButton

    init {
        this.orderDialogInterface = OrderDialogInterface
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.orderpop)

        orderpopBtn = findViewById(R.id.orderpopBtn)
        orderpopBtn

        orderpopBtn.setOnClickListener(this)

        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onClick(v: View?) {
        when(v){
            orderpopBtn -> {
                this.orderDialogInterface?.onOrderBtnClick()

            }
        }
    }
}