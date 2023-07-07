package com.f5.udemyuber.activities

import android.Manifest
import android.content.pm.PackageManager
import android.content.res.Resources
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
import com.f5.udemyuber.R
import com.f5.udemyuber.databinding.ActivityMapBinding
import com.f5.udemyuber.providers.Auth
import com.google.android.gms.common.api.Status
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.RectangularBounds
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import com.google.maps.android.SphericalUtil

class MapActivity : AppCompatActivity(), OnMapReadyCallback, Listener {

    private val TAG = "- MAP DEBUG -"

    private lateinit var binding: ActivityMapBinding
    private var googleMap: GoogleMap? = null
    private lateinit var easyWayLocation: EasyWayLocation
    private var myLocation: LatLng? = null
    private val authProvider = Auth()

    private var places: PlacesClient? = null
    private var autoCompleteOrigin: AutocompleteSupportFragment? = null
    private var autoCompleteDestination: AutocompleteSupportFragment? = null
    private var originName = ""
    private var destinationName = ""
    private var originLatLng: LatLng? = null
    private var destinationLatLng: LatLng? = null

    private var isLocationEnabled = false;

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

        startGooglePlaces()

    }

    var locationPermissions = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){ permissions ->
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            when{
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, true) ->{
                    Log.d(TAG, "Permiso concedido")
                    easyWayLocation?.startLocation()
                }
                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, true) ->{
                    Log.d(TAG, "Permiso concedido pero restricciones")
                    easyWayLocation?.startLocation()
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
        easyWayLocation?.startLocation()
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

        if(!isLocationEnabled) {
            isLocationEnabled = true
            limitSearch()
        }

    }

    override fun locationCancelled() {
    }

    private fun limitSearch(){
        val northSide = SphericalUtil.computeOffset(myLocation, 5000.0,0.0)
        val southSide = SphericalUtil.computeOffset(myLocation, 5000.0,180.0)

        autoCompleteOrigin?.setLocationBias((RectangularBounds.newInstance(southSide, northSide)))
        autoCompleteDestination?.setLocationBias((RectangularBounds.newInstance(southSide, northSide)))
    }

    private fun startGooglePlaces(){
        if(!Places.isInitialized()) Places.initialize(applicationContext, resources.getString(R.string.google_api_key))
        places = Places.createClient(this)
        instanceAutocompleteOrigin()
        instanceAutocompleteDestination()
    }

    private fun instanceAutocompleteOrigin(){
        autoCompleteOrigin = supportFragmentManager.findFragmentById(R.id.placeAutoCompleteOrigin) as AutocompleteSupportFragment
        autoCompleteOrigin?.setPlaceFields(
            listOf(
                Place.Field.ID,
                Place.Field.NAME,
                Place.Field.LAT_LNG,
                Place.Field.ADDRESS,
            )
        )
        autoCompleteOrigin?.setHint("Origen")
        autoCompleteOrigin?.setCountry("BO")
        autoCompleteOrigin?.setOnPlaceSelectedListener(object: PlaceSelectionListener{
            override fun onPlaceSelected(place: Place) {
                originName = place.name!!
                originLatLng = place.latLng
                Log.d(TAG, "onPlaceSelected")
            }
            override fun onError(p0: Status) {
            }
        })
    }

    private fun instanceAutocompleteDestination(){
        autoCompleteDestination = supportFragmentManager.findFragmentById(R.id.placeAutoCompleteDestination) as AutocompleteSupportFragment
        autoCompleteDestination?.setPlaceFields(
            listOf(
                Place.Field.ID,
                Place.Field.NAME,
                Place.Field.LAT_LNG,
                Place.Field.ADDRESS,
            )
        )
        autoCompleteDestination?.setHint("Destino")
        autoCompleteDestination?.setCountry("BO")
        autoCompleteDestination?.setOnPlaceSelectedListener(object: PlaceSelectionListener{
            override fun onPlaceSelected(place: Place) {
                destinationName = place.name!!
                destinationLatLng = place.latLng
                Log.d(TAG, "onPlaceSelected DESTINATION")
            }
            override fun onError(p0: Status) {
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        easyWayLocation!!.endUpdates()
    }
}