package org.bedu.proyectobedu.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.viewModels
import coil.api.load
import okhttp3.*
import org.bedu.proyectobedu.R
import org.bedu.proyectobedu.databinding.FragmentProfileBinding
import org.json.JSONObject
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList

class ProfileFragment : Fragment() {

    private val profileViewModel: ProfileViewModel by viewModels()
    private var _binding : FragmentProfileBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        binding.listView.adapter = ItemListAdapter(requireActivity(), profileViewModel.itemList)

        profileViewModel.profileLiveData.observe(viewLifecycleOwner,{profile ->
            binding.userName.text = "${profile.first_name} ${profile.last_name}"
            binding.email.text = "${profile.email}"
            binding.shapeableImageView.load(profile.avatar)
        })

        setUpListView()
        profileViewModel.fetchProfileData()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
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