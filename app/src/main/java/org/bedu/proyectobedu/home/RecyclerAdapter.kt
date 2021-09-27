package org.bedu.proyectobedu.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import org.bedu.proyectobedu.R
import org.bedu.proyectobedu.model.Product
import kotlin.random.Random


class RecyclerAdapter(val products: List<Product>): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(), View.OnClickListener {

    lateinit var listener: View.OnClickListener

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
        view.setOnClickListener(this)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(products[position])
        val action = HomeFragmentDirections.actionHomeFragmentToProductDetailsFragment(products[position])
        holder.itemView.setOnClickListener {
            Navigation.findNavController(holder.itemView).navigate(action)

        }
    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun setOnClickListener(listener : View.OnClickListener){
        this.listener = listener
    }

    override fun onClick(view: View?) {
        listener.let { it.onClick(view) }
    }

}