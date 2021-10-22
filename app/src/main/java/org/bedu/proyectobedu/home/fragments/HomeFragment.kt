package org.bedu.proyectobedu.home.fragments

import android.os.Bundle
import android.view.*
import android.view.View.GONE
import androidx.fragment.app.Fragment
import android.view.View.VISIBLE
import androidx.core.view.doOnPreDraw
import androidx.core.view.isVisible
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.google.android.material.transition.MaterialElevationScale
import io.realm.Realm
import org.bedu.proyectobedu.R
import org.bedu.proyectobedu.databinding.FragmentHomeBinding
import org.bedu.proyectobedu.home.RecyclerAdapter
import org.bedu.proyectobedu.home.model.Product


class HomeFragment : Fragment() {

    private val productsRequestUrl = "https://fakestoreapi.com/products"
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inflateBottomNavStub()
        showOptionsMenu()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        hideProgressBar()
        setUpRecyclerView(findProducts())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
    }


    private fun findProducts(): List<Product>{
        val realm = Realm.getDefaultInstance()
        return realm.where(Product::class.java).findAll()
    }


    private fun inflateBottomNavStub(){
        if (!requireActivity().findViewById<View>(R.id.navigation).isVisible){
            requireActivity().findViewById<ViewStub>(R.id.navigation).inflate()
        }
    }

    private fun showOptionsMenu() {
        requireActivity().findViewById<View>(R.id.appBarLayout).visibility = VISIBLE
    }

    private fun setUpRecyclerView(list: List<Product>) {
        val rAdapter = RecyclerAdapter(list) { itemView, product ->

            exitTransition = MaterialElevationScale(false).apply {
                duration = resources.getInteger(R.integer.transition_motion_duration_medium).toLong()
            }
            reenterTransition = MaterialElevationScale(true).apply {
                duration = resources.getInteger(R.integer.transition_motion_duration_medium).toLong()
            }

            val extras = FragmentNavigatorExtras(itemView to getString(R.string.productTransitionName))
            val action = HomeFragmentDirections.actionHomeFragmentToProductDetailsFragment(product)

            findNavController().navigate(action, extras)
        }
        binding.recycler.adapter = rAdapter
    }

    private fun showProgressBar(){
        binding.homeProgressBar.visibility = VISIBLE
        binding.nearProductsText.visibility = GONE
        binding.recycler.visibility = GONE
    }

    private fun hideProgressBar(){
        binding.homeProgressBar.visibility = GONE
        binding.nearProductsText.visibility = VISIBLE
        binding.recycler.visibility = VISIBLE
    }

}

