package org.bedu.proyectobedu.profile

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.bedu.proyectobedu.MainActivity.Companion.PERMISSION_ID
import org.bedu.proyectobedu.R
import org.bedu.proyectobedu.databinding.FragmentAdressBinding
import java.util.*

class AddressFragment : BottomSheetDialogFragment(){

    private var _binding : FragmentAdressBinding? = null
    private val binding get() = _binding!!
    private val addressModelView: AddressModelView by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addressModelView.init(requireActivity(), requireContext())
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAdressBinding.inflate(inflater, container, false)
        binding.closeBtn.setOnClickListener{
            dismiss()
        }
        addressModelView.gpsLiveData.observe(viewLifecycleOwner,{addressText ->
            binding.actualAdressText.text = addressText
        })

        binding.updateLocation.setOnClickListener {
            addressModelView.getLocation()
            if (addressModelView.gpsLiveData.value == null){
                Toast.makeText(context, "No se ha podido encontrar la ubicación", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }


}