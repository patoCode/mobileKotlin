package com.f5.navigationapp.infraestructure

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.f5.navigationapp.databinding.RvMenuItemBinding
import com.f5.navigationapp.databinding.RvOfferItemBinding

class AppAdapterOffer(var optionList: List<Offer>): RecyclerView.Adapter<AppAdapterOffer.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppAdapterOffer.ViewHolder {
        val binding = RvOfferItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = optionList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var ui = holder.binding
        var element = optionList[position]

        ui.tvCity.text = element.city
        ui.tvCountry.text = element.country
        ui.tvPrice.text = element.price.toPlainString()
        ui.vPicture.setBackgroundResource(element.picture)
    }

    inner class ViewHolder(val binding: RvOfferItemBinding): RecyclerView.ViewHolder(binding.root){}
}