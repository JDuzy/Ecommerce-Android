package org.bedu.proyectobedu.cart

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import coil.api.load
import org.bedu.proyectobedu.R

class CartItemListAdapter(val context: Activity, var array: ArrayList<CartItem>, private val cartViewModel: CartViewModel): ArrayAdapter<CartItem>(context, R.layout.profile_item, array) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = LayoutInflater.from(context)
        val itemView: View = inflater.inflate(R.layout.cart_item, null)

        initView(itemView, position)

        return itemView
    }


    private fun initView(view: View, position: Int){
        view.findViewById<TextView>(R.id.priceText).text = "$${array[position].getPrice().toString()}"
        view.findViewById<TextView>(R.id.productName).text = array[position].getTitle()
        view.findViewById<ImageView>(R.id.productImage).load(array[position].getImage())
        val amountText = view.findViewById<TextView>(R.id.amountText)
        amountText.text = array[position].amount.toString()

        view.findViewById<ImageView>(R.id.addProduct).setOnClickListener{
            array[position].product?.let { prod -> cartViewModel.addCartItem(prod) }
            amountText.text = array[position].amount.toString()
        }

        view.findViewById<ImageView>(R.id.deleteProduct).setOnClickListener{
            array[position].product?.let { prod -> cartViewModel.removeCartItem(prod) }
            /*amountText.text = array[position].amount.toString()*/

        }
    }
}