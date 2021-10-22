package org.bedu.proyectobedu.cart.repository

import io.realm.Realm
import org.bedu.proyectobedu.cart.model.CartItem
import org.bedu.proyectobedu.home.model.Product

class CartItemRepository {

    companion object{

        fun findCartItemByProduct(product: Product) : CartItem?{
            val realm = Realm.getDefaultInstance()
            return realm.where(CartItem::class.java).equalTo("productId", product.id).findFirst()
        }

        fun findAll() : List<CartItem>{
            val realm = Realm.getDefaultInstance()
            return realm.where(CartItem::class.java).findAll()
        }

        fun deleteAll(){
            val realm = Realm.getDefaultInstance()
            realm.beginTransaction()
            realm.delete(CartItem::class.java)
            realm.commitTransaction()
        }

        fun insertCartItem(product: Product, amount: Int) : CartItem {
            val realm = Realm.getDefaultInstance()
            realm.beginTransaction()
            val cartItem = realm.createObject(CartItem::class.java)
            cartItem.product = product
            cartItem.amount = amount
            cartItem.productId = product.id
            realm.commitTransaction()
            return cartItem
        }

        fun updateAmount(cartItem: CartItem, newAmount: Int){
            val realm = Realm.getDefaultInstance()
            realm.beginTransaction()
            val foundCartItem = realm.where(CartItem::class.java).equalTo("productId", cartItem.product?.id).findFirst()
            foundCartItem?.amount = newAmount
            realm.commitTransaction()
        }

        fun remove(cartItem : CartItem){
            val realm = Realm.getDefaultInstance()
            realm.beginTransaction()
            val foundCartItem = realm.where(CartItem::class.java).equalTo("productId", cartItem.product?.id).findFirst()
            foundCartItem?.deleteFromRealm()
            realm.commitTransaction()
        }

    }
}