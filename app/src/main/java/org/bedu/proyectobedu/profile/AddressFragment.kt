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

    lateinit var mFusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAdressBinding.inflate(inflater, container, false)
        binding.closeBtn.setOnClickListener{
            dismiss()
        }
        binding.updateLocation.setOnClickListener {
            getLocation()
        }
        return binding.root
    }

    //TODO: Abstract all this on a specific class
    private fun checkGranted(permission: String): Boolean{
        return ActivityCompat.checkSelfPermission(requireContext(), permission) == PackageManager.PERMISSION_GRANTED
    }

    private fun checkPermissions(): Boolean{
        return checkGranted(Manifest.permission.ACCESS_COARSE_LOCATION) && checkGranted(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    private fun requestPermissions(){
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_ID
        )
    }

    private fun isLocationEnabled(): Boolean{
        val locationManager: LocationManager = requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    @SuppressLint("MissingPermission")
    private fun getLocation(){
        if (checkPermissions()){
            if (isLocationEnabled()){
                mFusedLocationClient.lastLocation.addOnSuccessListener {
                    if (it != null){
                        val geocoder = Geocoder(requireContext(), Locale.getDefault())
                        val address = geocoder.getFromLocation(it.latitude, it.longitude, 1).get(0)
                        binding.actualAdressText.text = "${address.getAddressLine( 0)}, ${address.locality}"
                    }
                    else{
                        Toast.makeText(context, resources.getString(R.string.noLocationFound), Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else{
                turnOnGPS()
            }
        }
        else{
            requestPermissions()
        }
    }

    private fun turnOnGPS(){
        val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
        Toast.makeText(context, resources.getString(R.string.turnOnGPS), Toast.LENGTH_SHORT).show()
        startActivity(intent)
    }

}