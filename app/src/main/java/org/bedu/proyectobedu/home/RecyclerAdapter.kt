package org.bedu.proyectobedu.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import org.bedu.proyectobedu.R
import org.bedu.proyectobedu.dataclass.Product
import kotlin.random.Random



class RecyclerAdapter(val products: List<Product>, val listener: (View , Product) -> Unit): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val name = view.findViewById<TextView>(R.id.productName)
        private val price = view.findViewById<TextView>(R.id.priceText)
        private val ratingBar = view.findViewById<RatingBar>(R.id.ratingBar)
        private val ratings = view.findViewById<TextView>(R.id.ratings)
        private val image = view.findViewById<ImageView>(R.id.productImage)

        fun bind(product: Product){
            name.text = product.title
            price.text = "$${product.price}"
            ratings.text = (0..300).random().toString()
            ratingBar.rating = Random.nextDouble(0.0, 5.0).toFloat()
            image.load(product.image)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(products.get(position))
            holder.itemView.transitionName = "product_${products.get(position).title}_transition"
            holder.itemView.setOnClickListener{listener(holder.itemView, products.get(position))}
    }

    override fun getItemCount(): Int {
        return products.size
    }



}