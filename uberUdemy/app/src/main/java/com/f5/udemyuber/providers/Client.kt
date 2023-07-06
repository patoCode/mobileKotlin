package com.f5.udemyuber.providers

import com.f5.udemyuber.models.Client
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Client {

    val db = Firebase.firestore.collection("clients-uber")

    fun create(client: Client): Task<Void> {
        return db.document(client.id!!).set(client)
    }

}