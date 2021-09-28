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


class LoginFragment: Fragment() {
    lateinit var emailEditText: TextInputEditText
    lateinit var passwordEditText: TextInputEditText
    lateinit var loginBtn: Button
    lateinit var registrationBtn : Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        initViews(view)
        return view
    }

    private fun login(){
        if (validateInputs()) findNavController().navigate(R.id.action_loginFragment_to_homeFragment, null)
    }

    private fun validateInputs() : Boolean{
        val isEmailNotBlank : Boolean = EditTextInputValidator.validateEditText(emailEditText, getString(R.string.blank_field))
        val isPasswordNotBlank : Boolean = EditTextInputValidator.validateEditText(passwordEditText, getString(R.string.blank_field))
        return isEmailNotBlank && isPasswordNotBlank
    }


    private fun initViews(view: View){
        emailEditText = view.findViewById(R.id.loginEmailEditText)
        passwordEditText = view.findViewById(R.id.loginPasswordEditText)
        loginBtn = view.findViewById(R.id.loginBtn)
        loginBtn.setOnClickListener { login()  }
        registrationBtn = view.findViewById(R.id.registerHereBtn)
        registrationBtn.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment, null)
        }
    }

}