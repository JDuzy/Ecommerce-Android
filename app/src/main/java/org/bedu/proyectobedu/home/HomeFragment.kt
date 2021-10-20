package org.bedu.proyectobedu.home

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.View.GONE
import androidx.fragment.app.Fragment
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.core.view.doOnPreDraw
import androidx.core.view.isVisible
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import coil.api.load
import com.google.android.material.transition.MaterialElevationScale
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.runBlocking
import okhttp3.*
import org.bedu.proyectobedu.R
import org.bedu.proyectobedu.databinding.FragmentHomeBinding
import org.bedu.proyectobedu.dataclass.Product
import org.json.JSONObject
import java.io.IOException



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
        showProgressBar()
        fetchProducts()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
    }

    private fun fetchProducts() {
        val okHttpClient = OkHttpClient()
        val request = Request.Builder()
            .url(productsRequestUrl)
            .build()

        var result : List<Product>? = null

        okHttpClient.newCall(request).enqueue(object: Callback {

            override fun onFailure(call: Call, e: IOException) {
                Toast.makeText(context, resources.getString(R.string.request_error), Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                requireActivity().runOnUiThread{
                    if (response.isSuccessful && body != null){
                        val listProductType = object : TypeToken<List<Product>>() {}.type
                        result = Gson().fromJson(body, listProductType)
                        setUpRecyclerView(result)
                    }
                    else
                        Toast.makeText(context, resources.getString(R.string.products_not_found), Toast.LENGTH_SHORT).show()
                    hideProgressBar()
                }
            }
        })
    }


    private fun inflateBottomNavStub(){
        if (!requireActivity().findViewById<View>(R.id.navigation).isVisible){
            requireActivity().findViewById<ViewStub>(R.id.navigation).inflate()
        }
    }

    private fun showOptionsMenu() {
        requireActivity().findViewById<View>(R.id.appBarLayout).visibility = VISIBLE
    }

    private fun setUpRecyclerView(list: List<Product>?) {
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

