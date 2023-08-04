package com.f5.navigationapp.infraestructure

import java.math.BigDecimal

data class Offer(
    var price: BigDecimal,
    var country: String,
    var city: String,
    var picture:Int
    ) {

}