package com.f5.tablayout_new.viewpager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.f5.tablayout_new.databinding.ScreenBinding
import com.f5.tablayout_new.viewpager.model.PagerModel

class Adapter(var list: List<PagerModel>):RecyclerView.Adapter<Adapter.PagerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val binding = ScreenBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int){
        var ui = holder.binding
        var _screen = list[position]
        ui.clMain.background = ContextCompat.getDrawable(ui.root.context, _screen.bgColor)
        ui.ivPicture.setImageResource(_screen.image)
        ui.tvTitle.text = _screen.title
        ui.tvDescription.text = _screen.description
    }

    override fun getItemCount() = list.size

    inner class PagerViewHolder(val binding: ScreenBinding):RecyclerView.ViewHolder(binding.root){}

}