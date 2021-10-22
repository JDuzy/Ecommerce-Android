package org.bedu.proyectobedu.profile.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import org.bedu.proyectobedu.R
import org.bedu.proyectobedu.profile.model.Profile
import org.bedu.proyectobedu.profile.model.ProfileItem
import org.bedu.proyectobedu.profile.repository.ProfileRepository

class ProfileViewModel: ViewModel() {

    var profileLiveData: LiveData<Profile> = ProfileRepository.profileLiveData

    val itemList : ArrayList<ProfileItem> = arrayListOf(
        ProfileItem("Mis direcciones", R.drawable.ic_location_light, R.id.myLocations),
        ProfileItem("Métodos de pago", R.drawable.ic_credit_card, R.id.paymentMethod),
        ProfileItem("Pedidos", R.drawable.ic_history, R.id.orders),
        ProfileItem("Notificaciones", R.drawable.ic_notifications, R.id.notifications),
        ProfileItem("Cambiar contraseña", R.drawable.ic_lock_dark, R.id.password)
    )

    fun fetchProfileData() {
        ProfileRepository.fetchRandomProfile()
    }

}