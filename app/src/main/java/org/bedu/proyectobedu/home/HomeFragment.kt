package org.bedu.proyectobedu.home

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.view.View.VISIBLE
import androidx.appcompat.widget.ViewUtils
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import org.bedu.proyectobedu.R
import org.bedu.proyectobedu.dataclass.Product
import java.io.IOException


class HomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inflateBottomNavStub()
        showOptionsMenu()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        setUpRecyclerView(view)
        return view
    }

    private fun getJsonDataFromAsset(context: Context, fileName: String = "products.json"): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }

        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    private fun getProducts(context: Context): List<Product> {
        val jsonString = getJsonDataFromAsset(context)
        val listProductType = object : TypeToken<List<Product>>() {}.type
        return Gson().fromJson(jsonString, listProductType)
    }


    private fun inflateBottomNavStub(){
        if (!requireActivity().findViewById<View>(R.id.navigation).isVisible){
            requireActivity().findViewById<ViewStub>(R.id.navigation).inflate()
        }
    }

    private fun showOptionsMenu() {
        requireActivity().findViewById<View>(R.id.appBarLayout).visibility = VISIBLE
    }

    private fun setUpRecyclerView(view : View) {
        val recycler = view.findViewById<RecyclerView>(R.id.recycler)
        val rAdapter = RecyclerAdapter(getProducts(requireContext())) { product ->
            val action = HomeFragmentDirections.actionHomeFragmentToProductDetailsFragment(product)
            findNavController().navigate(action)
        }
        recycler.adapter = rAdapter
    }

}

