package com.anncode.offersandcoupons.model

import android.util.Log
import com.anncode.offersandcoupons.R
import com.anncode.offersandcoupons.presenter.CouponPresenter
import com.anncode.offersandcoupons.view.RecyclerCouponsAdapter
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//Finalmente llegando al repositorio hacemos lo mismo que las clases anteriores, aqui creamos una clase
//que hace referencia al repositorio e implementamos su interface al realizar esto nos pedira el IDE implementar
// los metodos que contiene esa interface y en este caso solo hay uno el cual es getCouponsAPI, dentro del constructor
//de la clase declaramos una variable de tipo interface llamado CouponPresenter siendo esta variable el que nos permitira,
//implementar su metodo para pasarle la información obtenida de la API al Presenter
class CouponRepositoryImpl(var couponPresenter: CouponPresenter): CouponRepository {

    //En este metodo podremos la logica para traer los datos de la API pero para ello primero debemos haber hecho unos preparativos antes
    //lo cual es definir el modelo de datos que se va a traer y eso se hizo dentro de la clase Coupon despues creamos otra clase
    //que se encargará se de configurar lo necesario para realizar la petición a la base de datos, es decir que dentro de la Clase ApiAdapter
    //tiene dentro la api key para poder acceder a la base de datos de la plataforma web, la dirección url, configuración del retrofit
    //para poder traer dicha información y que esa información lo convierta en un GSON, al tener esto, ya nadamas es definir por medio
    //de una interface llamada ApiService el endPoint que queremos consumir para traer la información especifica que necesitmos en esta caso
    //una lista de cupones, entonces al tener toodo esto configurado solo es necesario empezar a solicitar la información al servidor y toda esa
    //logica la hacemos en el metodo getCouponsAPI
    override fun getCouponsAPI() {
        var coupons: ArrayList<Coupon>? = ArrayList<Coupon>()
        var apiAdapter: ApiAdapter = ApiAdapter()
        val apiService = apiAdapter.getClientService()
        val call = apiService.getCoupons()
        call.enqueue(object : Callback<JsonObject> {
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Log.e("ERROR: ", t.message)
                t.stackTrace
            }
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                val offersJsonArray = response.body()?.getAsJsonArray("offers")
                offersJsonArray?.forEach { jsonElement: JsonElement ->
                    var jsonObject = jsonElement.asJsonObject
                    var coupon = Coupon(jsonObject)
                    coupons?.add(coupon)
                }
                //Entonces si la respuesta es satisfactoria le pasaremos la lista de cupones al presenter y eso seria todoo
                couponPresenter.showCoupons(coupons)
            }
        })
    }

}