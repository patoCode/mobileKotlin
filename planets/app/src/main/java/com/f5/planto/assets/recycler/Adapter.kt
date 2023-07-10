package com.f5.planto.assets.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.f5.planto.R
import com.f5.planto.databinding.ViewBinding

class Adapter(private val planetList: List<Planet>): RecyclerView.Adapter<MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.view, parent, false)
        val binding = ViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount() =  planetList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var dummyImg: Int? = null
        holder.binding.tvName.text = planetList[position].title
        holder.binding.tvGalaxy.text = planetList[position].galaxy
        holder.binding.tvDistance.text = planetList[position].distance + " m Km"
        holder.binding.tvGravity.text = planetList[position].gravity + " m/ss"

        when(planetList[position].title!!.toString().toLowerCase()){
            "mars" -> dummyImg = R.drawable.mars
            "moon" -> dummyImg = R.drawable.moon
            "neptune" -> dummyImg = R.drawable.neptune
            "earth" -> dummyImg = R.drawable.earth
            "venus" -> dummyImg = R.drawable.venus
            "uranus" -> dummyImg = R.drawable.uranus
            "sun" -> dummyImg = R.drawable.sun
            "saturn" -> dummyImg = R.drawable.saturn
            "jupiter" -> dummyImg = R.drawable.jupiter
            "mercury" -> dummyImg = R.drawable.mercury
            else ->  dummyImg = R.drawable.earth
        }
        holder.binding.ivImage.setImageResource(dummyImg!!)
    }

}