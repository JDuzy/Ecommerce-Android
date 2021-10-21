package org.bedu.proyectobedu.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import io.realm.Realm
import org.bedu.proyectobedu.R
import org.bedu.proyectobedu.cart.CartItem
import org.bedu.proyectobedu.cart.CartViewModel
import org.bedu.proyectobedu.databinding.FragmentPaymentBinding

class PaymentFragment: Fragment() {

    private val cartViewModel: CartViewModel by viewModels()
    private var _binding : FragmentPaymentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentPaymentBinding.inflate(inflater, container, false)
        initViews()
        binding.payBtn.setOnClickListener {
            cartViewModel.deleteAll()
            findNavController().navigate(R.id.action_paymentFragment_to_successfulPaymentFragment)
        }
        return binding.root
    }


    private fun initViews(){
        val allItems : List<CartItem> = cartViewModel.findAll()
        var subtotal: Float = 0f
        allItems.forEach { item -> subtotal += (item.getPrice()!! * item.amount)}
        binding.subtotalPrice.text = subtotal.toString()
        binding.totalPrice.text = (subtotal + 30f).toString()
    }
}