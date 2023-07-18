package com.f5.material_ui.recycler

import com.f5.material_ui.R

object setData {

    fun fillNavigators():List<MuiComponent>{
        var list = mutableListOf<MuiComponent>()
        list.add(MuiComponent(R.drawable.app_bar_top,"APP BARS - TOP", "Description del componente este ", "com.f5.material_ui.mui3.navigators.AppbarActivity" ))
        list.add(MuiComponent(R.drawable.app_bar_bottom,"APP BARS - BOTTOM", "Description del componente este ", "com.f5.material_ui.mui3.navigators.BottomAppbarActivity"))
        list.add(MuiComponent(R.drawable.bottom_navigation,"BOTTOM NAVIGATOR", "Description del componente este ", "com.f5.material_ui.mui3.navigators.BottomNavActivity"))
        return list
    }

    fun fillFormElements():List<MuiComponent>{
        var list = mutableListOf<MuiComponent>()
        list.add(MuiComponent(R.drawable.buttons, "All BUTTONS", "Description ", "com.f5.material_ui.mui3.forms.ButtonActivity"))
        list.add(MuiComponent(R.drawable.checkbox, "Checkbox - Chips", "Description ", "com.f5.material_ui.mui3.forms.CheckboxActivity"))
        list.add(MuiComponent(R.drawable.calendar, "DatePicker", "Description ", "com.f5.material_ui.mui3.forms.DatepickerActivity"))
        list.add(MuiComponent(R.drawable.dropdown, "DROPDOWN / LISTS / MENUS", "Description ", "com.f5.material_ui.mui3.forms.DropdownActivity"))

        return list
    }

}