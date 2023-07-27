package com.f5.login_registration.providers

import android.app.Activity
import com.f5.login_registration.R
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class AuthProvider {
    private val auth = FirebaseAuth.getInstance()

    fun register(username: String, password: String): Task<AuthResult> {
        return auth.createUserWithEmailAndPassword(username, password)
    }

    fun login(username: String, password: String): Task<AuthResult> {
        return auth.signInWithEmailAndPassword(username, password)
    }

    fun loginGoogle(activity: Activity): GoogleSignInClient {
        return GoogleSignIn.getClient(activity, clientApiGoogle() as GoogleSignInOptions)
    }

    private fun clientApiGoogle(): GoogleSignInOptions {
        return GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(R.string.web_id_google.toString())
            .requestEmail()
            .build() as GoogleSignInOptions
    }

}