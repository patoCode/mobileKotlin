package com.f5.calqlator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.f5.calqlator.databinding.ActivityMainBinding
import com.f5.calqlator.infraestructure.Calculator
import kotlin.math.sign

class MainActivity : AppCompatActivity() {
    private var TAG = "-CALCULATOR-"

    private lateinit var ui: ActivityMainBinding
    private lateinit var displayPreview: TextView
    private lateinit var displayHistoric: TextView

    private var calculator: Calculator = Calculator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityMainBinding.inflate(layoutInflater)
        setContentView(ui.root)

        // NUMEROS
        ui.one.setOnClickListener(listenerNumeric(ui.one.text.toString()))
        ui.two.setOnClickListener(listenerNumeric(ui.two.text.toString()))
        ui.three.setOnClickListener(listenerNumeric(ui.three.text.toString()))
        ui.four.setOnClickListener(listenerNumeric(ui.four.text.toString()))
        ui.five.setOnClickListener(listenerNumeric(ui.five.text.toString()))
        ui.six.setOnClickListener(listenerNumeric(ui.six.text.toString()))
        ui.seven.setOnClickListener(listenerNumeric(ui.seven.text.toString()))
        ui.eight.setOnClickListener(listenerNumeric(ui.eight.text.toString()))
        ui.nine.setOnClickListener(listenerNumeric(ui.nine.text.toString()))
        ui.zero.setOnClickListener(listenerNumeric(ui.zero.text.toString()))

        // AUXILIARES
        // ui.signDot.setOnClickListener(listenerDot(ui.signDot.text.toString()))
        // ui.signPercent.setOnClickListener(listenerPercent())
        ui.signCe.setOnClickListener(listenerClearAll())

        // OPERACIONES
        ui.signPlus.setOnClickListener(listenerSign(ui.signPlus.text.toString()))
        ui.signMinus.setOnClickListener(listenerSign(ui.signMinus.text.toString()))
        ui.signMultiply.setOnClickListener(listenerSign(ui.signMultiply.text.toString()))
        ui.signDivide.setOnClickListener(listenerSign(ui.signDivide.text.toString()))

        // ACTIONS
        ui.signEquals.setOnClickListener(listenerEquals())
        ui.erase.setOnClickListener(listenerErase())

        // DISPLAY
        displayPreview = ui.result
        displayHistoric = ui.preview
    }

    inner class listenerNumeric(var value: String): View.OnClickListener{
        override fun onClick(view: View?) {
            calculator.writeNumberOnPreview(value)
            displayPreview.text = calculator.getPreview()
        }
    }

    inner class listenerSign(var sign: String): View.OnClickListener{
        var result: Float = 0f
        override fun onClick(view: View?) {
            calculator.writeOperator(sign)
            displayPreview.text = calculator.getPreview()
            displayHistoric.text = calculator.getHistoric()
        }
    }

    inner class listenerEquals(): View.OnClickListener{
        override fun onClick(view: View?) {
            var _result = calculator.operateEquals()
            displayHistoric.text = ""
            displayPreview.text = calculator.getPreview()
        }
    }

    inner class listenerDot(var value: String): View.OnClickListener{
        override fun onClick(view: View?) {
            calculator.writeNumberOnPreview(".")
        }
    }

    inner class listenerErase(): View.OnClickListener{
        override fun onClick(view: View?) {
            calculator.erase()
            displayPreview.text = calculator.getPreview()
        }
    }

    inner class listenerClearAll():View.OnClickListener{
        override fun onClick(view: View?) {
            calculator.reset()
            resetScreen()
        }
    }

    fun resetScreen(){
        calculator.reset()
        displayHistoric.text = calculator.getHistoric()
        displayPreview.text = calculator.getPreview()
    }

}