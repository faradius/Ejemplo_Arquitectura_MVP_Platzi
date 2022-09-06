package com.anncode.offersandcoupons.presenter

import com.anncode.offersandcoupons.model.Coupon

interface CouponPresenter {

    //VIEW - Este es el metodo que permitirá pasarle la información a la capa de la vista
    fun showCoupons(coupons: ArrayList<Coupon>?)

    //INTERACTOR - Este es el metodo que permitirá la solicitud de información al interactor
    fun  getCoupons()


}