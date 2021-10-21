package org.bedu.proyectobedu.dataclass

import android.os.Parcel
import android.os.Parcelable
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import kotlin.random.Random
import kotlin.random.Random.Default.nextDouble

open class Product(): RealmObject(),
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readFloat(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
    )

    @PrimaryKey var id: Int? = null
    var title: String? = null
    var price: Float? = null
    var description: String? = null
    var category: String? = null
    var image: String? = null

    constructor(id: Int?, title: String, price: Float, description: String, category: String, image: String) : this() {
        this.id = id
        this.title = title
        this.price = price
        this.description = description
        this.category = category
        this.image = image
    }


    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p: Parcel?, p1: Int) {
        with(p!!) {
            writeInt(p1)
            writeString(title)
            writeFloat(price!!)
            writeString(description)
            writeString(category)
            writeString(image)
        }
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }
}
