package org.bedu.proyectobedu.profile

import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class AddressModelView(): ViewModel() {

    lateinit var gpsLiveData: LiveData<String>
    lateinit var gpsLocator: GPSLocator
    lateinit var mFusedLocationClient: FusedLocationProviderClient


    fun init(fragmentActivity: FragmentActivity, context: Context){
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(fragmentActivity)
        gpsLocator = GPSLocator(mFusedLocationClient, context, fragmentActivity)
        gpsLiveData = gpsLocator.gpsLiveData
    }

    fun getLocation(){
        gpsLocator.getLocation()
    }

}