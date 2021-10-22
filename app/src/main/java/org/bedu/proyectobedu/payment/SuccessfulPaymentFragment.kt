package org.bedu.proyectobedu.payment

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getColor
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import okhttp3.internal.notify
import org.bedu.proyectobedu.R
import org.bedu.proyectobedu.databinding.FragmentSuccessfulPaymentBinding

class SuccessfulPaymentFragment: Fragment() {

    private var _binding : FragmentSuccessfulPaymentBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentSuccessfulPaymentBinding.inflate(inflater, container, false)
        binding.mainMenuBtn.setOnClickListener {
            findNavController().navigate(R.id.action_successfulPaymentFragment_to_homeFragment)
        }
        return binding.root
    }


}