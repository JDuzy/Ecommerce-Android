package org.bedu.proyectobedu.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import org.bedu.proyectobedu.R
import org.bedu.proyectobedu.databinding.FragmentLoginBinding
import org.bedu.proyectobedu.databinding.FragmentRegistrationBinding

class RegistrationFragment : Fragment(){

    private var _binding : FragmentRegistrationBinding? = null
    private val binding get() = _binding!!


    lateinit var emailEditText: TextInputEditText
    lateinit var passwordEditText: TextInputEditText
    lateinit var phoneEditText: TextInputEditText
    lateinit var nameEditText: TextInputEditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        initViews()
        return binding.root
    }

    private fun validateInputs() : Boolean{
        val isEmailNotBlank : Boolean = EditTextInputValidator.validateEditText(emailEditText, getString(R.string.blank_field))
        val isPasswordNotBlank : Boolean = EditTextInputValidator.validateEditText(passwordEditText, getString(R.string.blank_field))
        val isPhoneNotBlank : Boolean = EditTextInputValidator.validateEditText(phoneEditText, getString(R.string.blank_field))
        val isNameNotBlank : Boolean = EditTextInputValidator.validateEditText(nameEditText, getString(R.string.blank_field))
        return isEmailNotBlank && isPasswordNotBlank && isPhoneNotBlank && isNameNotBlank
    }

    private fun initViews(){
        emailEditText = binding.registrationEmailEditText
        passwordEditText = binding.registrationPasswordEditText
        phoneEditText = binding.registrationPhoneEditText
        nameEditText = binding.registrationNameEditText
        binding.registrationBtn.setOnClickListener {
            register()
        }
    }

    private fun register(){
        if (validateInputs()) findNavController().navigate(R.id.action_registrationFragment_to_loginFragment, null)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}