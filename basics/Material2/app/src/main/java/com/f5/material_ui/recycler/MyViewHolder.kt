package com.f5.material_ui.recycler

import android.content.Intent
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.f5.material_ui.databinding.MuiItemBinding

class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val _ui = MuiItemBinding.bind(view)

    fun bind(component: MuiComponent){
        _ui.tvTitle.text = component.title
        _ui.tvDescription.text = component.title.toUpperCase() + " > " + component.description
        _ui.ivMainImage.setImageResource(component.picture)
    }
}