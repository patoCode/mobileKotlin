package com.f5.firebase_store.service

import com.f5.firebase_store.models.User
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FBUser {

    private val db = Firebase.firestore.collection("registration")

    fun create(user: User): Task<Void>{
        return db.document(user.id).set(user)
    }

}