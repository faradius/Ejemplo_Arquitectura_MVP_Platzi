package com.anncode.offersandcoupons.view

import com.anncode.offersandcoupons.model.Coupon

interface  CouponView {
    //VIEW - Este es el metodo que tendrá la lista de cupones que se implementará en la vista
    fun showCoupons(coupons: ArrayList<Coupon>?)

    //PRESENTER - Este es el metodo que obtendrá la lista de cupones y este se los pedirá al presenter
    fun getCoupons()


}