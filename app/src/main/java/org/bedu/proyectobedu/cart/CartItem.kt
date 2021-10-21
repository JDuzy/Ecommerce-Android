package org.bedu.proyectobedu.cart

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import org.bedu.proyectobedu.dataclass.Product

open class CartItem () : RealmObject(){

    var productId: Int? = null
    var product: Product? = null
    var amount: Int = 1


    fun getPrice(): Float? {
        return product?.price
    }

    fun getTitle(): String? {
        return product?.title
    }

    fun getImage(): String? {
        return product?.image
    }

    /*fun hasProduct(product: Product): Boolean {
        return this.product.equals(product)
    }*/

}