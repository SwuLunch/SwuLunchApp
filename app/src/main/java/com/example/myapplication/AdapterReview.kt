package com.example.myapplication


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text
import java.util.*
import kotlin.collections.ArrayList

class AdapterReview() : RecyclerView.Adapter<AdapterReview.HolderReview>() {

    private var context: Context?=null
    private var reviewList:ArrayList<ModelReview>?=null
    lateinit var myHelper : MainActivity.myDBHelper

    constructor(context: Context?, reviewList: ArrayList<ModelReview>?):this(){
        this.context = context
        this.reviewList = reviewList
    }

    inner class HolderReview(itemView: View): RecyclerView.ViewHolder(itemView){
        var imageIv : ImageView = itemView.findViewById<ImageView>(R.id.imageIv)
        var nameTv : TextView = itemView.findViewById<TextView>(R.id.nameTv)
        var contentTv: TextView = itemView.findViewById<TextView>(R.id.contentTv)
        var starTV: TextView = itemView.findViewById<TextView>(R.id.starTv)
        var whenTv: TextView = itemView.findViewById<TextView>(R.id.whenTv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderReview {
        return HolderReview(
            LayoutInflater.from(context).inflate(R.layout.review_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HolderReview, position: Int) {
        val model = reviewList!!.get(position)


        val id = model.id
        val name = model.name
        val image = model.image
        val contentt = model.contentt
        val stars = model.ratingstar
        val addedTime = model.addedTime
        Log.d("camera","url: "+image)
        val calendar1 = Calendar.getInstance(Locale.getDefault())
        calendar1.timeInMillis = addedTime.toLong()
        val timeAdded = android.text.format.DateFormat.format("yyyy-MM-dd", calendar1)

        holder.nameTv.text = name
        holder.whenTv.text = timeAdded
        holder.contentTv.text = contentt
        holder.starTV.text = "별점 : " + model.ratingstar
        holder.imageIv.setImageURI(Uri.parse(image))



        if(image == "null"){
            holder.imageIv.setImageResource(R.drawable.ic_person_black)
        }else
            holder.imageIv.setImageURI(Uri.parse(image))
 /*
        holder.itemView.setOnClickListener{
            val intent = Intent(context, ReviewDetailActivity::class.java)
            intent.putExtra("RECORD_ID",id)
            context!!.startActivity(intent)
        }*/

    }

    override fun getItemCount(): Int {
        return reviewList!!.size
    }


}