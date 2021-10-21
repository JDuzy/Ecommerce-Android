package org.bedu.proyectobedu

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.createObject
import org.bedu.proyectobedu.dataclass.Product
import org.json.JSONArray

class MainApp: Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)

        val array = JSONArray(getJsonString())

        val config = RealmConfiguration.Builder()
            .initialData{

                for (i in 0 until array.length()){
                    val product = it.createObject(Product::class.java, i)
                    val productJson = array.getJSONObject(i)
                    product.title = productJson.getString("title")
                    product.price = productJson.getString("price").toFloat()
                    product.description = productJson.getString("description")
                    product.category = productJson.getString("category")
                    product.image = productJson.getString("image")

                }
            }
            .deleteRealmIfMigrationNeeded()
            .name("realmDB.realm")
            .build()

        Realm.setDefaultConfiguration(config)
    }

    private fun getJsonString(): String{
        return applicationContext.assets.open("products.json").bufferedReader().use { it.readText() }
    }
}