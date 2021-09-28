package org.bedu.proyectobedu.dataclass

import android.os.Parcel
import android.os.Parcelable
import kotlin.random.Random
import kotlin.random.Random.Default.nextDouble

class Product(var id: Int, var title: String, var price: Float, var description: String, var category: String, var image: String ):
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readFloat(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p: Parcel?, p1: Int) {
        with(p!!) {
            writeInt(p1)
            writeString(title)
            writeFloat(price)
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
