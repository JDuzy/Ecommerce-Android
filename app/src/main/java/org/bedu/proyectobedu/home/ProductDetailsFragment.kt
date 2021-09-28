package org.bedu.proyectobedu.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.api.load
import org.bedu.proyectobedu.R
import kotlin.random.Random



class ProductDetailsFragment : Fragment() {

    private val args: ProductDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_product_details, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View){
        view.findViewById<TextView>(R.id.detailsProductName).text = args.product.title
        view.findViewById<TextView>(R.id.detailsPrice).text = "$${args.product.price}"
        view.findViewById<ImageView>(R.id.detailsProductImage).load(args.product.image)
        view.findViewById<RatingBar>(R.id.detailsRatingBar).rating = Random.nextDouble(0.0, 5.0).toFloat()
        view.findViewById<TextView>(R.id.detailsRatings).text = (0..300).random().toString()
        view.findViewById<TextView>(R.id.descriptionText).text = args.product.description
        view.findViewById<TextView>(R.id.interestPrice).text = "$${String.format("%.2f", (args.product.price/6))}"
        view.findViewById<Button>(R.id.addToCartBtn).setOnClickListener{
            findNavController().navigate(R.id.action_productDetailsFragment_to_cartFragment, null)
        }
    }

}