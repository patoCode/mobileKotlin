package com.f5.udemyuber.providers

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class Auth {

    val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun isLogged(): Boolean{
        return auth.currentUser != null
    }

    fun login(username: String, password: String): Task<AuthResult> {
    return auth.signInWithEmailAndPassword(username, password)
    }

    fun register(username: String, password: String): Task<AuthResult> {
        return auth.createUserWithEmailAndPassword(username, password)
    }

    fun getId(): String{
        return auth.currentUser?.uid ?: ""
    }
}