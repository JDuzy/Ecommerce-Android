package org.bedu.proyectobedu.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import okhttp3.*
import org.bedu.proyectobedu.R
import org.bedu.proyectobedu.databinding.FragmentLoginBinding
import java.io.IOException
import okhttp3.FormBody

import okhttp3.RequestBody





class LoginFragment: Fragment() {

    private val loginUrl = "https://reqres.in/api/login"

    private var _binding : FragmentLoginBinding? = null
    private val binding get() = _binding!!

    lateinit var emailEditText: TextInputEditText
    lateinit var passwordEditText: TextInputEditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        initViews()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun login(){
        if (validateInputs()) loginPostRequest(emailEditText.text.toString(), passwordEditText.text.toString() )
    }

    private fun validateInputs() : Boolean{
        val isEmailNotBlank : Boolean = EditTextInputValidator.validateEditText(emailEditText, getString(R.string.blank_field))
        val isPasswordNotBlank : Boolean = EditTextInputValidator.validateEditText(passwordEditText, getString(R.string.blank_field))
        return isEmailNotBlank && isPasswordNotBlank
    }


    private fun initViews(){
        emailEditText = binding.loginEmailEditText
        passwordEditText = binding.loginPasswordEditText
        binding.loginBtn.setOnClickListener { login()  }
        binding.registerHereBtn.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment, null)
        }
    }

    private fun loginPostRequest(email: String, password: String){
        val okHttpClient = OkHttpClient()

        val formBody: RequestBody = FormBody.Builder()
            .add("email", email)
            .add("password", password)
            .build()

        val request = Request.Builder()
            .url(loginUrl)
            .post(formBody)
            .build()

        okHttpClient.newCall(request).enqueue(object: Callback{

            override fun onFailure(call: Call, e: IOException) {
                Toast.makeText(context, resources.getString(R.string.request_error), Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call, response: Response) {
                 requireActivity().runOnUiThread{
                    if (response.code == 200)
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment, null)
                    else
                        Toast.makeText(context, resources.getString(R.string.user_not_found), Toast.LENGTH_SHORT).show()
                }

            }

        })
    }


}