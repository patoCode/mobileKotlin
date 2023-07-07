package com.f5.uberudemyclonedriver.activities

import android.Manifest
import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.easywaylocation.EasyWayLocation
import com.example.easywaylocation.Listener
import com.f5.uberudemyclonedriver.R
import com.f5.uberudemyclonedriver.databinding.ActivityMapBinding
import com.f5.uberudemyclonedriver.providers.Auth
import com.f5.uberudemyclonedriver.providers.Geo
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MapActivity : AppCompatActivity(), OnMapReadyCallback, Listener {

    private val TAG = "- LOCATION DEBUG -"

    private lateinit var binding: ActivityMapBinding
    private var googleMap: GoogleMap? = null
    private lateinit var easyWayLocation: EasyWayLocation
    private var myLocation: LatLng? = null
    private var markerDriver: Marker? = null
    private val geoProvider = Geo()
    private val authProvider = Auth()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val locationRequest = LocationRequest.create().apply {
            interval = 0
            fastestInterval = 0
            priority = Priority.PRIORITY_HIGH_ACCURACY
            smallestDisplacement = 1f
        }

        easyWayLocation = EasyWayLocation(this, locationRequest, false, false, this)

        locationPermissions.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ))

        binding.btnConect.setOnClickListener { connectDriver() }
        binding.btnDisconect.setOnClickListener { disconnectDriver() }

    }

    var locationPermissions = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){ permissions ->
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            when{
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, true) ->{
                    Log.d(TAG, "Permiso concedido")
                    checkIfDriverConnected()
                }
                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, true) ->{
                    Log.d(TAG, "Permiso concedido pero restricciones")
                    checkIfDriverConnected()
                }
                else -> {
                    Log.d(TAG, "Permiso NO concedido")
                }
            }
        } else {
            Log.d(TAG, "Permiso NO concedido")
        }
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
//        easyWayLocation?.startLocation()
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        googleMap?.isMyLocationEnabled = false

        try{
            val success = googleMap?.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(this, R.raw.style)
            )
            if(!success!!) Log.d("STYLE", " Not found resource")

        }catch(e: Resources.NotFoundException){
            Log.d("STYLE", "ERROR ${e.toString()}")
        }
    }

    override fun locationOn() {
    }

    override fun currentLocation(location: Location) {
        myLocation = LatLng(location.latitude, location.longitude)
        googleMap?.moveCamera(CameraUpdateFactory.newCameraPosition(
            CameraPosition.builder().target(myLocation!!).zoom(17f).build()
        ))
        addMarker()
        saveLocation()
    }

    override fun locationCancelled() {
    }

    fun checkIfDriverConnected(){
        geoProvider.getLocation(authProvider.getId()).addOnSuccessListener { document ->
            if(document.exists()){
                if(document.contains("l")) connectDriver()
                else showButtonConnect()
            } else {
                showButtonConnect()
            }
        }
    }

    private fun saveLocation(){
        if(myLocation != null){
            geoProvider.saveLocation(authProvider.getId(), myLocation!!)
        }
    }

    private fun disconnectDriver(){
        easyWayLocation?.endUpdates()
        if(myLocation != null) {
            geoProvider.removeLocation(authProvider.getId())
            showButtonConnect()
        }
    }

    private fun connectDriver(){
        easyWayLocation?.endUpdates()
        easyWayLocation?.startLocation()
        showButtonDisconnect()
    }

    private fun showButtonConnect(){
        binding.btnDisconect.visibility = View.GONE
        binding.btnConect.visibility = View.VISIBLE
    }

    private fun showButtonDisconnect(){
        binding.btnDisconect.visibility = View.VISIBLE
        binding.btnConect.visibility = View.GONE
    }

    private fun addMarker(){
        val drawable = ContextCompat.getDrawable(applicationContext, R.drawable.car_top_view)
        val markerIcon = getMarkerFromDrawable(drawable!!)
        if(markerDriver != null){
            markerDriver?.remove()
        }
        markerDriver = googleMap?.addMarker(
            MarkerOptions()
                .position(myLocation!!)
                .anchor(0.5f,0.5f)
                .flat(true)
                .icon(markerIcon)
        )!!
    }

    private fun getMarkerFromDrawable(drawable: Drawable): BitmapDescriptor{
        val canvas = Canvas()
        val bitmap = Bitmap.createBitmap(
            240,
            119,
            Bitmap.Config.ARGB_8888
        )
        canvas.setBitmap(bitmap)
        drawable.setBounds(0,0,100,100)
        drawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

     override fun onDestroy() {
        super.onDestroy()
        easyWayLocation!!.endUpdates()
    }
}