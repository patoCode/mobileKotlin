package com.f5.wweatherapp.viewmodel


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.f5.wweatherapp.model.WeatherModel
import com.f5.wweatherapp.service.WeatherApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MainViewModel(): ViewModel() {

    private val weatherApiService = WeatherApiService()
    private val disposable = CompositeDisposable()

    var weatherData = MutableLiveData<WeatherModel>()
    var weatherError = MutableLiveData<Boolean>()
    var weatherLoad = MutableLiveData<Boolean>()

    fun refreshData(cityName: String){
        getDataFromApi(cityName)
    }

    private fun getDataFromApi(cityName: String){
        weatherLoad.value = true
        disposable.add(
            weatherApiService.getDataService(cityName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<WeatherModel>(){
                    override fun onSuccess(value: WeatherModel) {
                        Log.d("-API_REST-", "${value.toString()}")
                        weatherData.value = value
                        weatherError.value = false
                    }

                    override fun onError(e: Throwable) {
                        weatherError.value = true
                        weatherLoad.value = false
                    }

                })

        )
    }

}