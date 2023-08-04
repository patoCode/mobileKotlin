package com.f5.calqlator.shared_preferences


import android.content.Context
import android.content.SharedPreferences

class SharedProvider(ctx: Context) {
    private val APP_SP = "f5_calculator_data"
    private val THEME_KEY = "dark_theme"

    private val preferences: SharedPreferences = ctx.getSharedPreferences(APP_SP, Context.MODE_PRIVATE)

    fun themeIsSave(): Int{
        return preferences.getInt(THEME_KEY, 0)
    }
    fun saveTheme(value: Int){
        preferences.edit().putInt(THEME_KEY, value).apply()
    }
}