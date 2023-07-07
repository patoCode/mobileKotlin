package com.f5.udemyuber.providers

import android.util.Log
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.GeoPoint
import org.imperiumlabs.geofirestore.GeoFirestore

class Geo {
    private final val TAG = "- GEO MESSAGE -"

    val collection = FirebaseFirestore.getInstance().collection("locations")
    val geoFirestore = GeoFirestore(collection)

    fun getLocation(idDrive: String): Task<DocumentSnapshot>{
        return collection.document(idDrive).get().addOnFailureListener{ exception ->
            Log.d(TAG, "ERROR ${exception.toString()}")
        }
    }

    fun saveLocation(idDriver: String, position: LatLng){
        geoFirestore.setLocation(idDriver, GeoPoint(position.latitude, position.longitude))
    }

    fun removeLocation(idDrive: String){
        collection.document(idDrive).delete()
    }

}