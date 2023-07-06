package com.f5.uberudemyclonedriver.providers

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.f5.uberudemyclonedriver.models.Driver as mDriver

class Driver {


    val db = Firebase.firestore.collection("drivers-uber")

    fun create(driver: mDriver): Task<Void> {
        return db.document(driver.id!!).set(driver)
    }
}