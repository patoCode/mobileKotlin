package com.f5.navigationapp.infraestructure

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.f5.navigationapp.databinding.RvMenuItemBinding

class AppAdapter(var optionList: List<MenuOption>): RecyclerView.Adapter<AppAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppAdapter.ViewHolder {
        val binding = RvMenuItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = optionList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var ui = holder.binding
        var element = optionList[position]
        ui.tvTitle.text = element.name
        ui.llContainerIcon.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(element.color)));
        ui.ivIcon.setImageResource(element.icon)

    }

    inner class ViewHolder(val binding: RvMenuItemBinding): RecyclerView.ViewHolder(binding.root){}
}