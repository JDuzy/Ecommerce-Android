package org.bedu.proyectobedu.model

import android.os.Parcel
import android.os.Parcelable

class Product(var id: Int, var title: String, var price: Float, var description: String, var category: String, var image: String):
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readFloat(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p: Parcel?, p1: Int) {
        p!!.writeInt(p1)
        p.writeString(title)
        p.writeFloat(price)
        p.writeString(description)
        p.writeString(category)
        p.writeString(image)

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
