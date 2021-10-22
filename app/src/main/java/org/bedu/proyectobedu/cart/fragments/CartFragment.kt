package org.bedu.proyectobedu.cart.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import org.bedu.proyectobedu.R
import org.bedu.proyectobedu.cart.model.CartItem
import org.bedu.proyectobedu.cart.CartItemListAdapter
import org.bedu.proyectobedu.cart.viewmodel.CartViewModel
import org.bedu.proyectobedu.databinding.FragmentCartBinding

class CartFragment : Fragment() {

    private var _binding : FragmentCartBinding? = null
    private val binding get() = _binding!!
    private val cartViewModel: CartViewModel by viewModels()
    lateinit var proceedWithPaymentBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        binding.cartListView.adapter = CartItemListAdapter(requireActivity(),
            cartViewModel.findAll() as ArrayList<CartItem>,cartViewModel)

        proceedWithPaymentBtn = binding.proceedWithPaymentBtn


        cartViewModel.cartItems.observe(viewLifecycleOwner, Observer {
            binding.cartListView.adapter = CartItemListAdapter(requireActivity(), cartViewModel.findAll() as ArrayList<CartItem>,cartViewModel)
            updatePaymentBtn()
        })
        updatePaymentBtn()

        binding.proceedWithPaymentBtn.setOnClickListener {
            findNavController().navigate(R.id.action_cartFragment_to_paymentFragment)
        }

        return binding.root
    }

    private fun updatePaymentBtn(){
        if (cartViewModel.isCartEmpty()){
            proceedWithPaymentBtn.visibility = GONE
        }
        else{
            proceedWithPaymentBtn.visibility = VISIBLE
        }

    }


}