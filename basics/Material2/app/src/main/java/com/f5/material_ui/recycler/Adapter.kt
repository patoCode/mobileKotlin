package com.f5.material_ui.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.f5.material_ui.R

class Adapter(private val componentList: List<MuiComponent>,
              private val onClickListener: OnClickListener):RecyclerView.Adapter<MyViewHolder>() {

    private var listener: OnClickListener? = onClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MyViewHolder(layoutInflater.inflate(R.layout.mui_item, parent, false))
    }

    override fun getItemCount() = componentList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = componentList[position]
        holder.itemView.setOnClickListener {
            listener?.onItemClick(item)
        }
        holder.bind(item)
    }

    class OnClickListener(val clickListener:(comp: MuiComponent) -> Unit){
        fun onItemClick(comp: MuiComponent) = clickListener(comp)
    }

}