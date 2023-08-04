package com.f5.calqlator.infraestructure.abstracts

interface IDisplay {


    fun writeCharacter(c: String)
    fun writeOperator(c: String)
    fun reset()
    fun isEmpty(): Boolean
    fun isLock(): Boolean
}