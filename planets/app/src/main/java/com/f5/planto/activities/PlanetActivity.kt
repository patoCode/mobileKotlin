package com.f5.planto.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.f5.planto.assets.recycler.Planet
import com.f5.planto.databinding.ActivityPlanetBinding

class PlanetActivity : AppCompatActivity() {

    private lateinit var obj: Planet
    private var image: Int? = null

    private lateinit var binding: ActivityPlanetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlanetBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.decorView.apply { systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN }

        binding.tvInfoFooter.setOnClickListener { it->
            val i = Intent(this, FinalActivity::class.java)
            startActivity(i)
        }

        obj = intent.getParcelableExtra("planet")!!
        image = intent.getIntExtra("image", -1)
        _setData(obj, image!!)


    }

    fun _setData(obj: Planet, image: Int){
        binding.tvName.text = obj.title
        binding.tvGalaxy.text = obj.galaxy
        binding.tvDistance.text = obj.distance + "m Km"
        binding.tvGravity.text = obj.gravity + "m/ss"
        binding.tvOverviewInfo.text = obj.overview
        binding.ivImagePlanet.setImageResource(image)
    }
}