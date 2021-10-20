package org.bedu.proyectobedu.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import coil.api.load
import okhttp3.*
import org.bedu.proyectobedu.R
import org.bedu.proyectobedu.databinding.FragmentProfileBinding
import org.json.JSONObject
import java.io.IOException

class ProfileFragment : Fragment() {

    private val profileRequestUrl = "https://reqres.in/api/users/"

    private var _binding : FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val itemList : ArrayList<ProfileItem> = arrayListOf(ProfileItem("Mis direcciones", R.drawable.ic_location_light, R.id.myLocations),
        ProfileItem("Métodos de pago", R.drawable.ic_credit_card, R.id.paymentMethod),
        ProfileItem("Pedidos", R.drawable.ic_history, R.id.orders),
        ProfileItem("Notificaciones", R.drawable.ic_notifications, R.id.notifications),
        ProfileItem("Cambiar contraseña", R.drawable.ic_lock_dark, R.id.password)
    )


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        binding.listView.adapter = ItemListAdapter(requireActivity(), itemList)
        setUpListView()
        fetchProfileData()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun fetchProfileData() {
        val okHttpClient = OkHttpClient()

        val request = Request.Builder()
            .url(profileRequestUrl + (1..12).random())
            .build()

        okHttpClient.newCall(request).enqueue(object: Callback {

            override fun onFailure(call: Call, e: IOException) {
                Toast.makeText(context, resources.getString(R.string.request_error), Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                requireActivity().runOnUiThread{
                    if (response.isSuccessful && body != null){
                        val jsonBody =  JSONObject(body)
                        val dataJson = jsonBody.getJSONObject("data")
                        binding.email.text = dataJson.getString("email")
                        binding.shapeableImageView.load(dataJson.getString("avatar"))
                        binding.userName.text = "${dataJson.getString("first_name")} ${dataJson.getString("last_name")}"
                    }
                    else
                        Toast.makeText(context, resources.getString(R.string.user_not_found), Toast.LENGTH_SHORT).show()
                }

            }

        })
    }

    private fun setUpListView(){
        binding.listView.onItemClickListener = AdapterView.OnItemClickListener{ parent , view, position, id ->
                when(view.id){
                    R.id.myLocations -> {
                        val addressFragment = AddressFragment()
                        addressFragment.show(parentFragmentManager,"Locationfragment")
                    }
                }
        }
    }



}