package org.bedu.proyectobedu.profile.model

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.LocationManager
import android.provider.Settings
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.FusedLocationProviderClient
import org.bedu.proyectobedu.MainActivity
import org.bedu.proyectobedu.R
import java.util.*

class GPSLocator(val mFusedLocationClient: FusedLocationProviderClient, val context: Context, val activityFragment: FragmentActivity) {

    private val MSG = "El GPS se encuentra apagado. Por favor enciendalo"
    val gpsLiveData = MutableLiveData<String>()

    private fun checkGranted(permission: String): Boolean{
        return ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
    }

    private fun checkPermissions(): Boolean{
        return checkGranted(Manifest.permission.ACCESS_COARSE_LOCATION) && checkGranted(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    private fun requestPermissions(){
        ActivityCompat.requestPermissions(
            activityFragment,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION),
            MainActivity.PERMISSION_ID
        )
    }

    private fun isLocationEnabled(): Boolean{
        val locationManager: LocationManager = activityFragment.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER)
    }

    @SuppressLint("MissingPermission")
    fun getLocation(){
        if (checkPermissions()){
            if (isLocationEnabled()){
                mFusedLocationClient.lastLocation.addOnSuccessListener {
                    if (it != null){
                        val geocoder = Geocoder(context, Locale.getDefault())
                        val address = geocoder.getFromLocation(it.latitude, it.longitude, 1).get(0)
                        val addressString = "${address.getAddressLine( 0)}, ${address.locality}"
                        gpsLiveData.postValue(addressString)
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
        Toast.makeText(context, MSG, Toast.LENGTH_SHORT).show()
        activityFragment.startActivity(intent)
    }
}