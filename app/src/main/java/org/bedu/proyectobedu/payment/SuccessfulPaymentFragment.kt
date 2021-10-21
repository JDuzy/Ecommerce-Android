package org.bedu.proyectobedu.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
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