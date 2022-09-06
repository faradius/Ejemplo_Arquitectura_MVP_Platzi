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

    //Se declara una variable de tipo interface para instanciarla despues el presenter
    private var couponPresenter: CouponPresenter ? = null
    //Se declara el recyclerView de forma global para poder ser accedida desde el metodo showCoupons
    private var rvCoupons: RecyclerView ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        //Instanciamos el presenter con la siguiente linea de codigo y este nos pedirá como parametro
        //una vista lo cual viene siendo nuestro MainActivity ya que implementa el couponView por eso le pasamos la referencia this,
        //en otras palabra el presentador adquiere la referencia del couponView
        //para entender mejor esto,CouponPresenterImpl nos pide como parametro una interface,
        // pero como estamos implementando la interface en esta clase es por eso que nos permite ponerle como parametro la referencia el ActivityMain
        couponPresenter = CouponPresenterImpl(this)

        //Declaramos nuestra vista o nuestro RecyclerView
        rvCoupons = findViewById(R.id.rvCoupons)
        //Se configura la forma de mostrar los datos en el recyclerView
        rvCoupons?.layoutManager = LinearLayoutManager(this)

        //Aqui ejecutamos el metodo getCoupons() para poder hacer la llamada para traer los cupones
        //Es decir se ejecuta la solicitud hacia el camino de ida
        getCoupons()
    }

    //Aqui se implementan los metodos de la interface CouponView

    //Con este metodo obtenemos los cupones
    override fun getCoupons() {
        //Al crear la instancia del presentador ahora si podemos utilizar sus metodos el cual vamos a ocupar su metodo
        //getCoupons derivado del presenter, por lo que indicamos o le pedimos al presenter que nos traiga los cupones a la capa de la vista
        couponPresenter?.getCoupons()
    }

    //Aqui mostramos los cupones en el recyclerView
    //En este metodo ya tiene en sus argumentos la lista de cupones que fue traida por el presenter
    override fun showCoupons(coupons: ArrayList<Coupon>?) {
        //Creamos un try catch para que la aplicación no colapse en caso de que no se haya obtenido la lista de cupones o que
        //la lista venga vacia
        try {
            //En el recyclerView ponemos nuestro adapter o forma de como se mostrara la información en nuestra vista
            //En el RecyclerCouponsAdapter nos pide dos parametro los cuales son una lista de cupones y eso se lo pasamos
            //mediante la variable coupons por que ahi esta la información de todos los cupones que le trajo el presentador
            //despues nos pide un vista o un layout de como se va a mostrar nuestra lista de cupones por lo tanto le pasamos
            // el layout card_coupon, cabe resaltar que no se manda a llamar el metodo showCoupons en el metodo onCreate del activity como
            //se hizo en el showCoupons por que como el recyclerView esta definido en el onCreate solo se le pasan los datos que se obtuvieron mediante
            //este metodo showCoupons
            rvCoupons!!.adapter = RecyclerCouponsAdapter(coupons,R.layout.card_coupon)
        }catch (e: Exception){
            e.printStackTrace()
        }

    }
}
