package com.example.hyperdesigntask.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hyperdesigntask.R
import com.example.hyperdesigntask.home.model.Data

class ShippmentAdapter(private val data : List<Data>?): RecyclerView.Adapter<ShippmentAdapter.MyHolder>() {
    class MyHolder(val row: View): RecyclerView.ViewHolder(row){
            var shippmentName = row.findViewById<TextView>(R.id.tv_shippment_name)
        var shippmentDesc = row.findViewById<TextView>(R.id.tv_shippment_description)
        var shippmentStatus = row.findViewById<TextView>(R.id.tv_status)
        var shippmentComment = row.findViewById<TextView>(R.id.tv_comment)
    }
    private lateinit var onItemClickListener: OnItemClickListener

    interface  OnItemClickListener{
        fun onItemClicked(id:Int)
    }
    fun setOnClickListener(listener: OnItemClickListener){
        onItemClickListener = listener

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val row = LayoutInflater.from(parent.context).inflate(R.layout.shippment_item,parent, false)
        return MyHolder(row)
    }

    override fun getItemCount(): Int {
        return data!!.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
       holder.shippmentName.text = data?.get(position)?.shipment_name
        holder.shippmentDesc.text = data?.get(position)?.description
        holder.shippmentComment.text = data?.get(position)?.comment
        holder.shippmentStatus.text= data?.get(position)?.status
        onItemClickListener.onItemClicked(data?.get(position)?.id as Int)
    }
}