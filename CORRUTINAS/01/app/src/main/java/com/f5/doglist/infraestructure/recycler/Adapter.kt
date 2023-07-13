package com.f5.doglist.infraestructure.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.f5.doglist.R

class Adapter(private val imagesList: List<String>): RecyclerView.Adapter<MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val li = LayoutInflater.from(parent.context)
        return MyViewHolder(li.inflate(R.layout.dog_item, parent, false))
    }

    override fun getItemCount(): Int = imagesList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item: String = imagesList[position]
        holder.bind(item)
    }

}