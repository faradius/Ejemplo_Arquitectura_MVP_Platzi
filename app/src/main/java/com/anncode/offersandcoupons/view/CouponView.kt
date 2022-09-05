package com.anncode.offersandcoupons.view

import com.anncode.offersandcoupons.model.Coupon

interface  CouponView {
    //VIEW - Muestra la lista de los cupones en la vista
    fun showCoupons(coupons: ArrayList<Coupon>?)

    //PRESENTER - Le pide información al presenter
    fun getCoupons()


}