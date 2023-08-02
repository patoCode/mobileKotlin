package com.f5.sharedpreferences.sp_client

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager


object SharedClient {

    val USER_ID = "USER_ID"
    val USER_PASSWORD = "PASSWORD"

    fun defaultPreferences(ctx: Context) : SharedPreferences = PreferenceManager.getDefaultSharedPreferences(ctx)

    fun customPreferences(ctx: Context, name: String): SharedPreferences = ctx.getSharedPreferences(name, Context.MODE_PRIVATE)

    inline fun SharedPreferences.editMe(operation: (SharedPreferences.Editor) -> Unit){
        val editMe = edit()
        operation(editMe)
        editMe.apply()
    }

    var SharedPreferences.userId
        get() = getInt(USER_ID, 0)
        set(value){
            editMe {
                it.putInt(USER_ID, value)
            }
        }

    var SharedPreferences.password
        get() = getString(USER_PASSWORD,"")
        set(value){
            editMe {
                it.putString(USER_PASSWORD, value)
            }
        }

    var SharedPreferences.clearValues
        get() = { }
        set(value) {
            editMe {
                it.clear()
            }
        }

}