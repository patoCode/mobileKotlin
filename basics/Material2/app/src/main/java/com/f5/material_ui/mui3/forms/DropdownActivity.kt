package com.f5.material_ui.mui3.forms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.annotation.MenuRes
import com.f5.material_ui.R
import com.f5.material_ui.databinding.ActivityDropdownBinding

class DropdownActivity : AppCompatActivity() {
    private val TAG = "DROPDOWN"
    private lateinit var _ui: ActivityDropdownBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _ui = ActivityDropdownBinding.inflate(layoutInflater)
        setContentView(_ui.root)
        registerForContextMenu(_ui.tvDescription)
        // POPUP MENU
        _ui.btnPopup.setOnClickListener { it ->
            showMenu(it, R.menu.popup_menu)
        }
        // EXPOSED DROPDOWN
        val items = listOf("Exposed 1","Exposed 2","Exposed 3","Exposed 4")
        val adapter = ArrayAdapter(this@DropdownActivity, R.layout.list_item, items)
        _ui.autoComplete.setAdapter(adapter)
    }

    override fun onCreateContextMenu(menu: ContextMenu?,
                                     v: View?,
                                     menuInfo: ContextMenu.ContextMenuInfo?){
        val tvDescripcion = v as TextView
        val ctx = applicationContext
        val inflater = menuInflater
        inflater.inflate(R.menu.dropdown_menu, menu)
    }

    private fun showMenu(v: View,@MenuRes dropdownMenu: Int) {
        val popup = PopupMenu(applicationContext, v)
        popup.menuInflater.inflate(dropdownMenu, popup.menu)
        popup.setOnMenuItemClickListener { menuItem: MenuItem ->
            Log.d(TAG, "CLICK ${menuItem.toString()}")
            true
        }
        popup.setOnDismissListener {it ->
            true
        }
        // Show the popup menu.
        popup.show()
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo
        return when(item.itemId){
            R.id.option1 -> {
                Log.d(TAG, "OPTION 1")
                true
            }
            R.id.option2 -> {
                Log.d(TAG, "OPTION 2")
                true
            }
            R.id.option3 -> {
                Log.d(TAG, "OPTION 3")
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }
}