package com.anncode.offersandcoupons.presenter

import com.anncode.offersandcoupons.model.Coupon
import com.anncode.offersandcoupons.model.CouponsInteractor
import com.anncode.offersandcoupons.model.CouponsInteractorImpl
import com.anncode.offersandcoupons.view.CouponView

/*En primer lugar, Creamos una clase que represente el presenter por lo tanto la clase se llama CouponPresenterImpl
//En segundo lugar, implementamos su interface llamado CouponPresenter, este nos obligará a implementar sus metodos ya que es un contrato que debe cumplir
//de forma obligatoria, los metodos que se implementan son getCoupons y showCoupons
//En tercer lugar debemos de crear siempre dos instancias una que va dirigirse a la capa de la vista y el otro a la capa del modelo
//La primera instancia que es el CouponView va en el contructor de la clase CouponPresenterImpl por que cuando se creé la instancia
//del presenter en la vista nos va a pedir la vista y por ende le pasaremos el activity, ahora la otra instancia va estar dentro de la clase
//por que este nos va a servir para comunicarnos con el Interactor*/
class CouponPresenterImpl(var couponView: CouponView): CouponPresenter {

    /*Ahora creamos una variable llamado CouponInteractor que es de tipo interface (CouponInteractor) que se le asignará
    //o implementará la clase hija que es CouponsInteractorImpl, es decir se instancia de la clase padre que es CouponsInteractor con una clase
    //hija que es CouponsInteractorImpl ya que su interface esta implementada en esa clase, ya que la interface se puede considerar una clase ya que es la clase mas abstracta que tenenmos en programación
    //y dentro de esta instancia nos pide como parametro una interface por lo tanto le ponemos this ya que la interface esta implementada dentro de la
    //clase CouponPresenterImpl nos referiremos a esta clase en si, esto hace que se cumpla el principio liskov substitution derivado de SOLID, que nos
    //indica poder usar una clase hija para sustituir a una clase padre,
    //pero a toda esta explicación lo podemos resumir que queremos comunicarnos con el interactor mediante esta instancia y eso lo hacemos
    //en el metodo getCoupons implementando el metodo del interactor getCouponsAPI */

    private var couponInteractor: CouponsInteractor = CouponsInteractorImpl(this)

    //Nota: Ahi que recordar que queremos comunicarnos a apartir del presentador al repositorio por lo tanto tenemos que crear un
    //canal de comunicación para lograr esto es necesario pasar por el interactor hasta llegar al repositorio por lo tanto si queremos verlo de otra forma
    //con esta linea de codigo couponInteractor: CouponsInteractor = CouponsInteractorImpl(this)
    //decimos que queremos comunicarnos con el interactor pero quien lo pide? pues el presentador por eso el this

    //Estos son los metodos que se implementaron mediante la interface de CouponPresenter
    override fun getCoupons() {
        //Ahora bien como ya se creo la instancia para poder comunicarnos con los metodos del interactor ya podremos
        //pedirle información acerca de los cupones.
        couponInteractor.getCouponsAPI()
    }

    //En esta parte del codigo venimos acarreando los datos obtenidos de la capa del modelo y esos datos estan dentro
    //del parametro del metodo showCoupons siendo este una lista de cupones
    override fun showCoupons(coupons: ArrayList<Coupon>?) {
        //aqui lo que estamos haciendo es pasarle a la capa de la vista los cupones obtenidos de la capa del modelo
        couponView.showCoupons(coupons)
    }
    //de regreso del repositorio, ya tenemos los datos que se habian solicitados desde el presenter al respositorio
    // y esos datos los tiene almacenados la función showCoupons dentro de su parametro coupons de tipo arraylist ya que
    //la información traida es una lista de cupones

}

/*Explicación de la arquitectura
  En cada caso de uso de la aplicación se debe de dividir tres capas que es el model view presenter
  es decir en este caso de uso es mostrar una lista de cupones pues debemos estructurar la información de esta forma:

   CouponActivity debe tener su interface CouponView
   CouponPresenterImpl debe terner su interface CouponPresenter (ponemos CouponPresenterImpl por que indicamos que ahi vamos a implementar su interface y que es la clase hija de CouponPresenter)
   CouponModelImpl deber tener su interface CouponModel, pero esto no se realizará asi ya que dentro de la capa de modelo influyen otros factores

   Es decir que aqui va haber en la capa de modelo dos entidades importantes los cuales son
   Interactor: es el que tiene como función buscar la información al repositorio de una base de datos online o una base de datos local,
               esta información se lo solicitará el presentador.
   Repositorio: es el que se encarga de buscar los datos a una base de datos online o local, esto depende de lo que le haya pedido el interactor,
                y una vez traido dicha información se lo pasará al presentador.

   Estas entidades representa la capa del modelo
   */