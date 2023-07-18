package com.f5.material_ui.mui3.containers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.f5.material_ui.R
import com.f5.material_ui.databinding.ActivityMenuContainerBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MenuContainerActivity : AppCompatActivity() {
    private lateinit var _ui: ActivityMenuContainerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _ui = ActivityMenuContainerBinding.inflate(layoutInflater)
        setContentView(_ui.root)

        _ui.btnAlertDialog.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("set title")
                .setMessage("Lorem Ipsum es un texto de marcador de posición comúnmente utilizado en las industrias gráficas, gráficas y editoriales para previsualizar diseños y maquetas visuales.")
                .setNeutralButton("NEUTRAL"){dialog, which -> }
                .setNegativeButton("NO"){dialog, which -> }
                .setPositiveButton("SI"){dialog, which -> }
                .show()
        }

        // SIMPLE DIALOG
        val items = arrayOf("Item 1", "Item 2", "Item 3")
        _ui.btnSimpleDialog.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("set title")
                .setItems(items){dialog, which -> }
                .setCancelable(false)
                .show()
        }
        //  CONFIRM DIALOG
        val items2 = arrayOf("Item 1", "Item 2", "Item 3")
        val checkedItem = 1
        _ui.btnConfirmDialog.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("CONFIRM DIALOG")
                .setNeutralButton("CANCEL") { dialog, which ->
                }
                .setPositiveButton("ACCEPT") { dialog, which ->
                }
                .setSingleChoiceItems(items2, checkedItem) { dialog, which ->
                }
                .show()
        }

    }
}