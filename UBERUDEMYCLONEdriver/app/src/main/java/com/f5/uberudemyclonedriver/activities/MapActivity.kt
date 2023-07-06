package com.f5.uberudemyclonedriver.activities

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.easywaylocation.EasyWayLocation
import com.example.easywaylocation.Listener
import com.f5.uberudemyclonedriver.R
import com.f5.uberudemyclonedriver.databinding.ActivityMapBinding
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng

class MapActivity : AppCompatActivity(), OnMapReadyCallback, Listener {

    private lateinit var binding: ActivityMapBinding
    private var googleMap: GoogleMap? = null
    private var easyWayLocation: EasyWayLocation? = null
    private var myLocation: LatLng? = null

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

    }


    var locationPermissions = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){ permissions ->
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            when{
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, true) ->{
                    Log.d("- LOCATION -", "Permiso concedido")
                    Log.d("- LOCATION DEBUG -", easyWayLocation?.altitude.toString())
                }
                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, true) ->{
                    Log.d("- LOCATION -", "Permiso concedido pero restricciones")
                }
                else -> {
                    Log.d("- LOCATION -", "Permiso NO concedido")
                }
            }
        } else {
            Log.d("- LOCATION -", "Permiso NO concedido")
        }
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
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
        googleMap?.isMyLocationEnabled = true
    }

    override fun locationOn() {
    }

    override fun currentLocation(location: Location) {
        myLocation = LatLng(location.latitude, location.longitude)
        googleMap?.moveCamera(CameraUpdateFactory.newCameraPosition(
            CameraPosition.builder().target(myLocation!!).zoom(17f).build()
        ))
    }

    override fun locationCancelled() {
    }

     override fun onDestroy() {
        super.onDestroy()
        easyWayLocation!!.endUpdates()
    }
}