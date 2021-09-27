package org.bedu.proyectobedu.login

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.fragment_login.*
import org.bedu.proyectobedu.R


import android.view.ViewStub
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateLayoutParams
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView


class LoginFragment: Fragment() {
    lateinit var emailEditText: TextInputEditText
    lateinit var passwordEditText: TextInputEditText
    lateinit var loginBtn: Button
    lateinit var regisrationBtn : Button
    lateinit var bottom: BottomNavigationView
    lateinit var optionsMenu: AppBarLayout


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bottom = requireActivity().findViewById(R.id.navigation)
        bottom.visibility = GONE
        optionsMenu = requireActivity().findViewById(R.id.appBarLayout)
        optionsMenu.visibility = GONE

        val view = inflater.inflate(R.layout.fragment_login, container, false)
        initViews(view)
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val bottomNavigation: BottomNavigationView? = activity?.findViewById(R.id.navigation)
        bottomNavigation?.visibility = GONE
    }


    private fun login(){
        if (validateInputs()) {
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment, null)
        }
    }

    private fun validateInputs() : Boolean{
        val isEmailNotBlank : Boolean = validateEditText(emailEditText)
        val ispasswordNotBlank : Boolean = validateEditText(passwordEditText)
        return isEmailNotBlank && ispasswordNotBlank

    }


    private fun validateEditText(editText: TextInputEditText) : Boolean{
        if(editText.text.let { it.isNullOrBlank() }){
            editText.error = getString(R.string.blank_field)
            return false
        }
        return true
    }


    private fun initViews(view: View){
        emailEditText = view.findViewById(R.id.loginEmailEditText)
        passwordEditText = view.findViewById(R.id.loginPasswordEditText)
        loginBtn = view.findViewById(R.id.loginBtn)
        loginBtn.setOnClickListener { login()  }
        regisrationBtn = view.findViewById(R.id.registerHereBtn)
        regisrationBtn.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment, null)
        }
    }

}