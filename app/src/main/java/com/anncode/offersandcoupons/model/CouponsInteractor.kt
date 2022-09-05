package com.anncode.offersandcoupons.model

interface  CouponsInteractor {
    //REPOSITORY REMOTE - le pide información al repositorio encargado de una base de datos remoto
    fun getCouponsAPI()
}