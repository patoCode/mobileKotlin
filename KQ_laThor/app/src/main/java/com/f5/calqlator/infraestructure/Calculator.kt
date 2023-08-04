package com.f5.calqlator.infraestructure

import android.util.Log
import com.f5.calqlator.infraestructure.concret.Display
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.sign

class Calculator{

    var TAG = "-CALCULATOR-"

    var preview: Display = Display()
    var historic: Display = Display()

    fun getPreview() = preview.content
    fun getHistoric() = historic.content

    fun writeNumberOnPreview(number: String){
        preview.writeCharacter(number)
    }

    fun writeOperator(operator: String){
        if(historic.sign.equals("") ){
            historic.sign = operator
            historic.numericValue = preview.content.toBigDecimal()
            historic.content = preview.content.plus(operator)
            preview.reset()
        } else {
            historic.numericValue = operate()
            historic.sign = operator
            historic.content = preview.numericValue.toString().plus(operator)
        }
    }

    fun operateEquals(): BigDecimal{
        historic.numericValue = operate()
        historic.content = historic.numericValue.toString()
        preview.numericValue = historic.numericValue
        preview.content = historic.numericValue.toString()
        historic.sign = ""
        return historic.numericValue.setScale(2, RoundingMode.FLOOR)
    }

    fun reset(){
        historic.reset()
        preview.reset()
    }

    fun erase(){
        preview.content = preview.content.dropLast(1)
    }

    private fun operate(): BigDecimal{
        var result = BigDecimal.ZERO
        when(historic.sign){
            "+"-> result = historic.numericValue.plus(preview.numericValue)
            "-"-> result = historic.numericValue.minus(preview.numericValue)
            "x"-> result = historic.numericValue.times(preview.numericValue)
            "÷"-> result = historic.numericValue.divide(preview.numericValue, 2, RoundingMode.HALF_UP)
            else -> return result
        }
        preview.reset()
        Log.d(TAG, "EQUALS ${result.toString()}")
        return result
    }



}