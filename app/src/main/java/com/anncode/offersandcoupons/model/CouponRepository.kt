package com.anncode.offersandcoupons.model

interface CouponRepository {

    //Es el metodo que se implementará en el repositorio para traer los cupones mediante el api
    fun getCouponsAPI()
}