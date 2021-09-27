package org.bedu.proyectobedu.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.bedu.proyectobedu.R

class RegistrationFragment : Fragment(){
    lateinit var registerBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_registration, container, false)
        registerBtn = view.findViewById(R.id.registrationBtn)
        registerBtn.setOnClickListener {
            findNavController().navigate(R.id.action_registrationFragment_to_loginFragment, null)
        }
        return view
    }
}