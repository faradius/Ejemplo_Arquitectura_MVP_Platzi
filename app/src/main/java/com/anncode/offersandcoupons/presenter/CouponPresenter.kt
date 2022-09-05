package com.anncode.offersandcoupons.presenter

import com.anncode.offersandcoupons.model.Coupon

interface CouponPresenter {

    //VIEW - Le pasa una lista de cupones a la vista
    fun showCoupons(coupons: ArrayList<Coupon>?)

    //INTERACTOR - Le pide información al interactor
    fun  getCoupons()


}