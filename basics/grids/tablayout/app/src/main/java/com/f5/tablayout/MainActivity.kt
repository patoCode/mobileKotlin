package com.f5.tablayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.f5.tablayout.databinding.ActivityMainBinding
import com.f5.tablayout.models.Board
import com.f5.tablayout.viewpager.Adapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var boardList = listOf(
            Board(R.color.colorPrimary, R.drawable.pic_01, "LOREM 01", "Lorem ipsum dolor sit amet, ipsum dolor sit amet"),
            Board(R.color.colorSecondary, R.drawable.pic_02, "LOREM 02", "Lorem ipsum dolor sit amet, ipsum dolor sit amet"),
            Board(R.color.colorAccent, R.drawable.pic_03, "LOREM 03", "Lorem ipsum dolor sit amet, ipsum dolor sit amet")
        )
        val adapter = Adapter(boardList)
        binding.vpMain.adapter = adapter
        // binding.vpMain.orientation = ViewPager2.ORIENTATION_VERTICAL
    }
}