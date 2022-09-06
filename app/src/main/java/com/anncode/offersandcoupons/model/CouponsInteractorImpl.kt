package com.anncode.offersandcoupons.model

import com.anncode.offersandcoupons.presenter.CouponPresenter
//Despues de explicar el Presenter debemos pasar por el Interactor, aqui de igual manera implementamos su interface,
//en esta caso solo sera un metodo que se implemente el cual es getCouponAPI(), ahora en el contructor de la clase
//CouponsInteractorImpl crearemos una variable de tipo interface, esta variable hace referencia al presenter,
//esto es por que al instanciar la clase del Interactor en la clase CouponPresenterImpl debemos pedirle una interface derivado del Presenter
//esto es con propositos de poder comunicar el Interactor y el Presenter
class CouponsInteractorImpl(var couponPresenter: CouponPresenter): CouponsInteractor {

    //Ahora bien, aqui creamos la instancia del repositorio y como parametro le pasamos el couponPresenter, esto se hace
    //por que al final la peticion de los datos lo hace el presente mas bien el los esta solicitando y eso se lo necesitamos hacer
    //saber al repositorio, es por eso esta linea de codigo, tenemos que recordar que estamos construyendo un canal de comunicación
    //desde el presenter hasta el repositorio y aqui hacemos esto, pasandole la voz al repositorio de que el presenter necesita tales datos,
    //provenientes del interactor
    private var couponRepository : CouponRepository = CouponRepositoryImpl(couponPresenter)

    //Como ya se menciono antes aqui se implemento el metodo getCouponsAPI siendo este el metodo que hace referencia a la solicitud de dicha
    //información al repositorio
    override fun getCouponsAPI() {
        //pero para hacer eso necesitamos utilizar el metodo getCouponsAPI del repositorio para asi poder realizar correctamente la peticion de los datos
        //es por eso que arriba se realizo la instancia y que al final esos datos los necesita el presenter
        couponRepository.getCouponsAPI()
    }

}