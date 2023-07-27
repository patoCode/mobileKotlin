package com.f5.tablayout.viewpager

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.f5.tablayout.databinding.BoardBinding

import com.f5.tablayout.R
import com.f5.tablayout.models.Board

class Adapter(private val boardList: List<Board>): RecyclerView.Adapter<BoardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.board, parent, false)
        val binding = BoardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BoardViewHolder(binding)
    }

    override fun getItemCount() = boardList.size

    override fun onBindViewHolder(holder: BoardViewHolder, position: Int) {
        var _ui = holder.ui
        var board = boardList[position]
        _ui.llContainerBoard.background = ContextCompat.getDrawable(_ui.root.context, board.bg)
        _ui.ivPicture.setImageResource(board.img)
        _ui.tvTitle. text = board.title
        _ui.tvDescription.text= board.description
        if(position + 1 == boardList.size)
            _ui.btnNext.text = "Finalizar"
    }
}