package com.f5.wweatherapp.view

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.f5.wweatherapp.databinding.ActivityMainBinding
import com.f5.wweatherapp.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewmodel: MainViewModel
    private lateinit var GET: SharedPreferences
    private lateinit var SET: SharedPreferences.Editor
    private val URI = "https://openweathermap.org/img/wn/"
    private val CITY_FIELD = "cityName"
    private val CITY_DEFAULT = "Cochabamba"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.decorView.apply { systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN }

        GET = getSharedPreferences(packageName, MODE_PRIVATE)
        SET = GET.edit()

        viewmodel = ViewModelProvider(this).get(MainViewModel::class.java)
        var cName = GET.getString(CITY_FIELD, CITY_DEFAULT)

        binding.etCity.setText(cName.toString().capitalize())

        viewmodel.refreshData(cName!!)
        getLiveData()

        binding.srlSwipe.setOnRefreshListener {
            binding.llMain.visibility = View.GONE
            binding.tvError.visibility = View.GONE
            binding.pbLoader.visibility = View.GONE

            var cityName = GET.getString(CITY_FIELD, cName)
            binding.etCity.setText(cityName)
            viewmodel.refreshData(cityName!!)
            binding.srlSwipe.isRefreshing = false
        }

        binding.ivSearchIcon.setOnClickListener {
            val cityName = binding.etCity.text.toString()
            SET.putString(CITY_FIELD, cityName)
            SET.apply()
            viewmodel.refreshData(cityName!!)
            getLiveData()
        }

    }

    private fun getLiveData() {
        viewmodel.weatherData.observe(this, Observer { data ->
            data.let{
                binding.llMain.visibility = View.VISIBLE
                binding.pbLoader.visibility = View.GONE
                //EDO/PMO011061
                binding.tvTemperatureValue.text = data.main.temp.toString() + " °C"
                binding.tvCountryCode.text = data.sys.country.capitalize()
                binding.tvCityName.text = data.name.capitalize()
                binding.tvHumidity.text = data.main.humidity.toString()
                binding.tvWindSpeed.text = data.wind.speed.toString()
                binding.tvLat.text = data.coord.lat.toString()
                binding.tvLng.text = data.coord.lon.toString()

                Glide.with(this).load(URI+data.weather.get(0).icon+"@2x.png")
                    .into(binding.ivIcon)
            }
        })

        viewmodel.weatherLoad.observe(this, Observer{ load ->
            load?.let{
                if(it){
                    binding.pbLoader.visibility = View.VISIBLE
                    binding.tvError.visibility = View.GONE
                    binding.llMain.visibility = View.GONE
                } else {
                    binding.pbLoader.visibility = View.GONE
                }
            }
        })

        viewmodel.weatherError.observe(this, Observer{error ->
            error?.let{
                if(it){
                    binding.pbLoader.visibility = View.GONE
                    binding.tvError.visibility = View.VISIBLE
                    binding.llMain.visibility = View.GONE
                } else{
                    binding.tvError.visibility = View.GONE
                }
            }
        })

    }
}