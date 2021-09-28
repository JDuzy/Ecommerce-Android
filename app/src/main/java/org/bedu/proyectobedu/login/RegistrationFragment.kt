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

class RegistrationFragment : Fragment(){
    lateinit var registerBtn: Button
    lateinit var emailEditText: TextInputEditText
    lateinit var passwordEditText: TextInputEditText
    lateinit var phoneEditText: TextInputEditText
    lateinit var nameEditText: TextInputEditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_registration, container, false)
        initViews(view)
        return view
    }

    private fun validateInputs() : Boolean{
        val isEmailNotBlank : Boolean = EditTextInputValidator.validateEditText(emailEditText, getString(R.string.blank_field))
        val isPasswordNotBlank : Boolean = EditTextInputValidator.validateEditText(passwordEditText, getString(R.string.blank_field))
        val isPhoneNotBlank : Boolean = EditTextInputValidator.validateEditText(phoneEditText, getString(R.string.blank_field))
        val isNameNotBlank : Boolean = EditTextInputValidator.validateEditText(nameEditText, getString(R.string.blank_field))
        return isEmailNotBlank && isPasswordNotBlank && isPhoneNotBlank && isNameNotBlank
    }

    private fun initViews(view: View){
        emailEditText = view.findViewById(R.id.registrationEmailEditText)
        passwordEditText = view.findViewById(R.id.registrationPasswordEditText)
        phoneEditText = view.findViewById(R.id.registrationPhoneEditText)
        nameEditText = view.findViewById(R.id.registrationNameEditText)
        registerBtn = view.findViewById(R.id.registrationBtn)
        registerBtn.setOnClickListener {
            register()
        }
    }

    private fun register(){
        if (validateInputs()) findNavController().navigate(R.id.action_registrationFragment_to_loginFragment, null)
    }
}