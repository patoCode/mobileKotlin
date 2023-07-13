package com.f5.doglist.infraestructure.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.f5.doglist.databinding.DogItemBinding
import com.squareup.picasso.Picasso

class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val _ui = DogItemBinding.bind(view)

    fun bind(image: String){
        Picasso.get().load(image).into(_ui.ivDog)
    }

}