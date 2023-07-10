package com.f5.practicefirebasestore.activities

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import com.f5.practicefirebasestore.databinding.ActivityMainBinding
import org.json.JSONObject
import java.net.URL
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private val TAG = "- WEATHER_LOG -"
    private lateinit var binding: ActivityMainBinding
    val CITY = "Cochabamba"
    val API_KEY = "5bb7a8a879e2d1f70031de70e986dbbd"
    val URI = "https://api.openweathermap.org/data/2.5/weather?q=$CITY&units=metric&appid=$API_KEY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        whatherTask().execute()
    }

    inner class whatherTask(): AsyncTask<String, Void, String>(){

        override fun onPreExecute() {
            super.onPreExecute()
            binding.pbLoader.visibility = View.VISIBLE
            binding.rlMainContainer.visibility = View.GONE
            binding.tvError.visibility = View.GONE
        }

        override fun doInBackground(vararg p0: String?): String? {
            var response: String?
            try {
                response = URL(URI).readText(Charsets.UTF_8)
            } catch(e: Exception){
                response = null
            }
            return response
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            try {
                /* TODO MOVE */
                val jsonObject = JSONObject(result)
                val main = jsonObject.getJSONObject("main")
                val sys = jsonObject.getJSONObject("sys")
                val wind = jsonObject.getJSONObject("wind")
                val weather = jsonObject.getJSONArray("weather").getJSONObject(0)
                val updatedAt = jsonObject.getLong("dt")
                val updatedAtText = "Ultima actualización: " + SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH).format(Date(updatedAt*1000))
                val temp = main.getLong("temp").toInt().toString() + "°C"
                val tempMin = "Min. Temp: " + main.getLong("temp_min").toInt().toString()+ "°C"
                val tempMax = "Máx. Temp: " + main.getLong("temp_max").toInt().toString()+ "°C"
                val pressure = main.getString("pressure")
                val humidity = main.getString("humidity")
                val sunrise = sys.getLong("sunrise")
                val sunset = sys.getLong("sunset")
                val windSpeed = wind.getString("speed")
                val weatherDescription = weather.getString("description")
                val address = jsonObject.getString("name") + ", " + sys.getString("country")

                binding.tvAddress.text = address
                binding.tvUpdate.text = updatedAtText
                binding.tvStatus.text = weatherDescription.capitalize()
                binding.tvTemperature.text = temp
                binding.tvMinimunTemperature.text = tempMin
                binding.tvMaximunTemperature.text = tempMax
                binding.tvSunrise.text = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunrise*1000)).toString().toLowerCase()
                binding.tvSunset.text = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunset*1000)).toString().toLowerCase()
                binding.tvWind.text = windSpeed
                binding.tvPressure.text = pressure
                binding.tvHumidity.text = humidity
                binding.tvInfo.text = "Powered by f5\n patoCode"

                binding.pbLoader.visibility = View.GONE
                binding.rlMainContainer.visibility = View.VISIBLE

            } catch (e: Exception){

                Log.d(TAG, "EXCEPTION ${e.message.toString()}")

                binding.pbLoader.visibility = View.GONE
                binding.tvError.visibility = View.VISIBLE
            }

        }

    }

}