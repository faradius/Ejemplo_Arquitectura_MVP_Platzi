package com.anncode.offersandcoupons.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.anncode.offersandcoupons.R
import com.anncode.offersandcoupons.model.ApiAdapter
import com.anncode.offersandcoupons.model.Coupon
import com.anncode.offersandcoupons.presenter.CouponPresenter
import com.anncode.offersandcoupons.presenter.CouponPresenterImpl
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MainActivity : AppCompatActivity(),  CouponView{

    //Se declara la instancia de la interfaz del CouponPresenter en el activityCoupons
    private var couponPresenter: CouponPresenter ? = null
    //Se declara el recyclerView de forma global
    private var rvCoupons: RecyclerView ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()


        couponPresenter = CouponPresenterImpl(this)

        //Se vincula la vista del recyclerView en el activity
        rvCoupons = findViewById(R.id.rvCoupons)
        //Se configura la forma de mostrar los datos en el recyclerView
        rvCoupons?.layoutManager = LinearLayoutManager(this)

        getCoupons()
    }

    //Estos son los metodos implementados provenientes de la interface CouponView
    override fun getCoupons() {
        //Aqui le estamos indicando que queremos obtener los cupones al prensentador por medio de la interfaz
        couponPresenter?.getCoupons()
    }

    override fun showCoupons(coupons: ArrayList<Coupon>?) {
        try {
            rvCoupons!!.adapter = RecyclerCouponsAdapter(coupons,R.layout.card_coupon)
        }catch (e: Exception){
            e.printStackTrace()
        }

    }
}
