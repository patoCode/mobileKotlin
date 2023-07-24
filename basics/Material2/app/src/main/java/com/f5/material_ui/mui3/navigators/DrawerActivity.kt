package com.f5.material_ui.mui3.navigators

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.f5.material_ui.R
import com.f5.material_ui.databinding.ActivityDrawerBinding
import com.google.android.material.navigation.NavigationView

class DrawerActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{
    private lateinit var _ui: ActivityDrawerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _ui = ActivityDrawerBinding.inflate(layoutInflater)

        setSupportActionBar(_ui.toolbar)
        _ui.navView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(this, _ui.drawerLayout, _ui.toolbar, R.string.drawer_open, R.string.drawer_close)
        _ui.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        setContentView(_ui.root)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        _ui.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (_ui.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            _ui.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}