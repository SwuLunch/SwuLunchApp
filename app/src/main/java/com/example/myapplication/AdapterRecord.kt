package com.example.myapplication


import android.content.Context
import android.content.Intent
import android.net.Uri
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

class AdapterRecord() : RecyclerView.Adapter<AdapterRecord.HolderRecord>() {

    private var context: Context?=null
    private var recordList:ArrayList<ModelRecord>?=null

    constructor(context: Context?, recordList: ArrayList<ModelRecord>?):this(){
        this.context = context
        this.recordList = recordList
    }

    inner class HolderRecord(itemView: View): RecyclerView.ViewHolder(itemView){
        var nameTv : TextView = itemView.findViewById<TextView>(R.id.nameTv)
        var addressTv : TextView = itemView.findViewById<TextView>(R.id.addressTv)
        var timeTv : TextView = itemView.findViewById<TextView>(R.id.timeTv)
        var contentTv : TextView = itemView.findViewById<TextView>(R.id.contentTv)
        var addedTimeTv : TextView = itemView.findViewById<TextView>(R.id.addedDateTv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderRecord {
        return HolderRecord(
            LayoutInflater.from(context).inflate(R.layout.row_record, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HolderRecord, position: Int) {
        val model = recordList!!.get(position)

        val id = model.id
        val name = model.name
        val address = model.address
        val time = model.time
        val contentt = model.contentt
        val addedTime = model.addedTime
        val updatedTime = model.updatedTime

        val calendar1 = Calendar.getInstance(Locale.getDefault())
        calendar1.timeInMillis = addedTime.toLong()
        val timeAdded = android.text.format.DateFormat.format("yyyy/MM/dd", calendar1)

        holder.nameTv.text = name
        holder.addressTv.text = address
        holder.timeTv.text = time
        holder.contentTv.text = contentt
        holder.addedTimeTv.text = timeAdded



        holder.itemView.setOnClickListener{
            val intent = Intent(context, RecordDetailActivity::class.java)
            intent.putExtra("RECORD_ID",id)
            context!!.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return recordList!!.size
    }
}