package com.f5.weather_material.activities

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.f5.weather_material.adapters.HourlyAdapter
import com.f5.weather_material.adapters.setData
import com.f5.weather_material.databinding.ActivityMainBinding
import com.f5.weather_material.domains.utils
import java.net.URL

class MainActivity : AppCompatActivity() {

    private val TAG = "- WEATHER_LOG -"
    private lateinit var binding: ActivityMainBinding
    val CITY = "Cochabamba"
    val API_KEY = "cy1NEY39BW1LyNhOl6YlVE1Dnt89bein"
    val URI = "https://api.tomorrow.io/v4/weather/forecast?location=$CITY&timesteps=daily&units=metric&apikey=$API_KEY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.apply { systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        weatherTask().execute()

        initRecycler()
    }


    inner class weatherTask(): AsyncTask<String, Void, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg p0: String?): String {
            var response: String?
            try {
                response = URL(URI).readText(Charsets.UTF_8)
            } catch(e: Exception){
                response = null
            }
            Log.d(TAG, "RESPONSE ${response}")
            return response!!
        }

        override fun onPostExecute(result: String?) {
            utils.jsonToListForWeek(result!!)
        }

    }

    fun initRecycler(){
        binding.rvWeek.adapter = HourlyAdapter(setData.setHourly())
        binding.rvWeek.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
    }
}