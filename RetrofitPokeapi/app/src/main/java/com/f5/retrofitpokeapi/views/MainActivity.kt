package com.f5.retrofitpokeapi.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import com.f5.retrofitpokeapi.databinding.ActivityMainBinding
import com.f5.retrofitpokeapi.models.PokemonModel
import com.f5.retrofitpokeapi.service.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private val TAG = "RETROFIT_DATA"
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)




        var retrofit = RetrofitClient.consumidorApi.getPokemon()
        retrofit.enqueue(object: Callback<PokemonModel>{
            override fun onResponse(call: Call<PokemonModel>, response: Response<PokemonModel>) {
                Log.d(TAG, "${response.body().toString()}")
            }

            override fun onFailure(call: Call<PokemonModel>, t: Throwable) {
                Toast.makeText(this@MainActivity, "MAL", Toast.LENGTH_LONG).show()
            }

        })

    }
}