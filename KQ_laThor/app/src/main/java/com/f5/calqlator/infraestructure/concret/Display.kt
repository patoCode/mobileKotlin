package com.f5.calqlator.infraestructure.concret

import android.util.Log
import com.f5.calqlator.infraestructure.abstracts.IDisplay
import java.math.BigDecimal
import kotlin.math.sign

class Display(var _empty: Boolean = true,
              var _lock: Boolean = false,
              var content: String = "",
              var numericValue: BigDecimal = BigDecimal.ZERO,
              var sign: String = ""): IDisplay {

    private var TAG = "-DISPLAY-"

    override fun isEmpty() = _empty
    override fun isLock() = _lock

    override fun writeCharacter(c: String) {
        if(isEmpty())
            content = c
        else
            content = content.plus(c)

        numericValue = content.toBigDecimal()
        _empty = false
    }

    override fun writeOperator(c: String) {
        content = content.plus(c)
        _lock = true
        _empty = true
    }

    override fun reset() {
        sign = ""
        numericValue = BigDecimal.ZERO
        content = "0"
        _empty = true
        _lock = false
    }
}