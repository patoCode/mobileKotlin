package com.f5.crud_sp.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.f5.crud_sp.databinding.FragmentCreateBinding
import com.f5.crud_sp.infraestructure.DatePicker
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class CreateFragment : Fragment() {
    private var _binding: FragmentCreateBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private fun showCalendar() {
        val datePicker = DatePicker{ day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(requireActivity().supportFragmentManager, "datePicker")
    }

    private fun onDateSelected(day: Int, month: Int, year: Int) {
        var c = Calendar.getInstance();
        c.set(Calendar.YEAR, year)
        c.set(Calendar.MONTH, month)
        c.set(Calendar.DAY_OF_MONTH, day)
        binding.tieCumpleano.setText(SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).format(c.getTime()))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateBinding.inflate(inflater, container, false)
        val date = binding.tieCumpleano
        date.setOnClickListener {
            showCalendar()
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = CreateFragment()
    }
}