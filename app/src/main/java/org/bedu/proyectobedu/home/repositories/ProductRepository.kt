package org.bedu.proyectobedu.home.repositories

import io.realm.Realm
import org.bedu.proyectobedu.home.model.Product

class ProductRepository {

    companion object{

         fun findAll(): List<Product>{
            val realm = Realm.getDefaultInstance()
            return realm.where(Product::class.java).findAll()
        }
    }
}