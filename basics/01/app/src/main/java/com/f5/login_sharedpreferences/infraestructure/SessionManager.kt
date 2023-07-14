package com.f5.login_sharedpreferences.infraestructure

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.f5.login_sharedpreferences.models.User

class SessionManager(val ctx: Context) {

    private var sharedPreferences: SharedPreferences
    private var editor: SharedPreferences.Editor
    private val SHARED_PREF_NAME = "app_session"
    private val SESSION_KEY = "session_user"

    init{
        sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }

    fun saveSession(user: User){
        editor.putInt(SESSION_KEY, user.id).commit()
    }

    fun getSession(): Int{
        return sharedPreferences.getInt(SESSION_KEY, -1)
    }

    fun removeSession(){
        editor.putInt(SESSION_KEY, -1).commit()
    }
}