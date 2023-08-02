package com.f5.calqlator

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.f5.calqlator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var TAG = "-CALCULATOR-"
    private var pending = false

    private lateinit var ui: ActivityMainBinding
    private lateinit var resultTextView: TextView
    private lateinit var previewTextView: TextView

    private var firstNumber: Float = 0f
    private var secondNumber: Float = 0f


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityMainBinding.inflate(layoutInflater)
        setContentView(ui.root)
        resultTextView = ui.result
        previewTextView = ui.preview

        ui.signDot.setOnClickListener(listenerDot(ui.signDot.text.toString()))
        ui.signCe.setOnClickListener(listenerClearAll())

        ui.signPlus.setOnClickListener(listenerSign(ui.signPlus.text.toString()))
        ui.signMinus.setOnClickListener(listenerSign(ui.signMinus.text.toString()))
        ui.signMultiply.setOnClickListener(listenerSign(ui.signMultiply.text.toString()))
        ui.signDivide.setOnClickListener(listenerSign(ui.signDivide.text.toString()))

        ui.signEquals.setOnClickListener(listenerSign(ui.signEquals.text.toString()))
        ui.erase.setOnClickListener(listenerErase())

        ui.one.setOnClickListener(listenerNumeric(ui.one.text.toString()))
        ui.two.setOnClickListener(listenerNumeric(ui.two.text.toString()))
        ui.three.setOnClickListener(listenerNumeric(ui.three.text.toString()))
        ui.four.setOnClickListener(listenerNumeric(ui.four.text.toString()))
        ui.five.setOnClickListener(listenerNumeric(ui.five.text.toString()))
        ui.six.setOnClickListener(listenerNumeric(ui.six.text.toString()))
        ui.seven.setOnClickListener(listenerNumeric(ui.seven.text.toString()))
        ui.eight.setOnClickListener(listenerNumeric(ui.eight.text.toString()))
        ui.nine.setOnClickListener(listenerNumeric(ui.nine.text.toString()))
    }

    inner class listenerNumeric(var value: String): View.OnClickListener{
        override fun onClick(view: View?) {
            if(resultTextView.text.toString().equals("0"))
                resultTextView.text = value
            else
                resultTextView.text = resultTextView.text.toString().plus(value)
        }
    }


    inner class listenerSign(var sign: String): View.OnClickListener{
        override fun onClick(view: View?) {
            if (firstNumber == 0f){
                firstNumber = resultTextView.text.toString().toFloat()
            } else {
                if(secondNumber == 0f) {
                    secondNumber = resultTextView.text.toString().toFloat()
                }
            }
        }



    }


    inner class listenerDot(var value: String): View.OnClickListener{
        override fun onClick(view: View?) {
            resultTextView.text = resultTextView.text.toString().plus(value)
        }
    }


    inner class listenerErase(): View.OnClickListener{
        override fun onClick(view: View?) {
            if(resultTextView.text.toString().length <= 1)
                resultTextView.text = "0"
            else
                resultTextView.text = resultTextView.text.toString().dropLast(1)
        }
    }


    inner class listenerClearAll():View.OnClickListener{
        override fun onClick(view: View?) {
            firstNumber = 0f
            secondNumber = 0f
            resultTextView.text = "0"
            previewTextView.text = ""
        }
    }

//    inner class listenerEqual(): View.OnClickListener{
//        override fun onClick(view: View?) {
//            previewTextView.text = resultTextView.text.toString().plus(value)
//            resultTextView.text = ""
//        }
//    }
}