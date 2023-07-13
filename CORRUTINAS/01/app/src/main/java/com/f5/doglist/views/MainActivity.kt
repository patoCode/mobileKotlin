package com.f5.doglist.views

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.f5.doglist.databinding.ActivityMainBinding
import com.f5.doglist.infraestructure.recycler.Adapter
import com.f5.doglist.service.APIService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity(), OnQueryTextListener{
    private val TAG = "-DOGLIST-"

    private lateinit var _ui: ActivityMainBinding
    private lateinit var adapter: Adapter
    private val dogImages = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _ui = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_ui.root)


        initRecyclerView()
        _ui.svSearch.setOnQueryTextListener(this)

    }

    private fun initRecyclerView() {
        adapter = Adapter(dogImages)
        _ui.rvList.layoutManager = LinearLayoutManager(this)
        _ui.rvList.adapter = adapter
    }

    private fun getRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breed/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun searchByRace(query: String){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                //https://dog.ceo/api/breed/boxer/images
                val call = getRetrofit().create(APIService::class.java).getDogsByRace("${query}/images")
                Log.d(TAG, call.message().toString())
                val puppies = call.body()
                runOnUiThread {
                    if(call.isSuccessful){
                        val images = puppies?.message ?: emptyList()
                        dogImages.clear()
                        dogImages.addAll(images)
                        adapter.notifyDataSetChanged()
                    } else {
                        showError()
                    }
                }

            } catch (e: Exception){
                runOnUiThread {
                    showError()
                    Log.d(TAG, "${e.message.toString()}")
                }
            }
        }
    }

    private fun showError() {
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_LONG).show()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(!query.isNullOrEmpty()){
            searchByRace(query.toLowerCase())
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

}