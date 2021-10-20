package org.bedu.proyectobedu.home

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.api.load
import com.google.android.material.color.MaterialColors
import com.google.android.material.transition.MaterialContainerTransform
import org.bedu.proyectobedu.R
import org.bedu.proyectobedu.databinding.FragmentProductDetailsBinding
import kotlin.random.Random


class ProductDetailsFragment : Fragment() {

    private var _binding : FragmentProductDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: ProductDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        _binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        initViews()

        sharedElementEnterTransition = MaterialContainerTransform().apply {
            // The drawing view is the id of the view above which the container transform
            // will play in z-space.
            drawingViewId = binding.root.rootView.id
            duration = resources.getInteger(R.integer.transition_motion_duration_medium).toLong()
            // Set the color of the scrim to transparent as we also want to animate the
            // list fragment out of view
            scrimColor = Color.TRANSPARENT
            setAllContainerColors(resources.getColor(R.color.secondaryTextColor))
        }

        return binding.root
    }

    private fun initViews(){
        binding.detailsProductName.text = args.product.title
        binding.detailsPrice.text = "$${args.product.price}"
        binding.detailsProductImage.load(args.product.image)
        binding.detailsRatingBar.rating = Random.nextDouble(0.0, 5.0).toFloat()
        binding.detailsRatings.text = (0..300).random().toString()
        binding.descriptionText.text = args.product.description
        binding.interestPrice.text = "$${String.format("%.2f", (args.product.price/6))}"
        binding.addToCartBtn.setOnClickListener{
            findNavController().navigate(R.id.action_productDetailsFragment_to_cartFragment, null)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}