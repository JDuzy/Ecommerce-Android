package org.bedu.proyectobedu.payment.fragments

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
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDeepLinkBuilder
import androidx.navigation.fragment.findNavController
import org.bedu.proyectobedu.MainActivity
import org.bedu.proyectobedu.R
import org.bedu.proyectobedu.cart.model.CartItem
import org.bedu.proyectobedu.cart.viewmodel.CartViewModel
import org.bedu.proyectobedu.databinding.FragmentPaymentBinding

class PaymentFragment: Fragment() {


    private val CHANNEL_OTHERS = "OTROS"
    private val cartViewModel: CartViewModel by viewModels()
    private var _binding: FragmentPaymentBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            setNotificationChannel()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPaymentBinding.inflate(inflater, container, false)
        initViews()
        binding.payBtn.setOnClickListener {
            cartViewModel.deleteAll()
            touchNotification()
            findNavController().navigate(R.id.action_paymentFragment_to_successfulPaymentFragment)
        }
        return binding.root
    }


    private fun initViews() {
        val allItems: List<CartItem> = cartViewModel.findAll()
        var subtotal: Float = 0f
        allItems.forEach { item -> subtotal += (item.getPrice()!! * item.amount) }
        binding.subtotalPrice.text = subtotal.toString()
        binding.totalPrice.text = (subtotal + 30f).toString()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setNotificationChannel() {
        val name = getString(R.string.channel_payments)
        val descriptionText = getString(R.string.payment_descriptions)
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_OTHERS, name, importance).apply {
            description = descriptionText
        }

        val notificationManager: NotificationManager =
            requireActivity().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }


    private fun touchNotification() {

        val pendingIntent = NavDeepLinkBuilder(requireContext())
            .setComponentName(MainActivity::class.java)
            .setGraph(R.navigation.mobile_navigation)
            .setDestination(R.id.successfulPaymentFragment)
            .createPendingIntent()


        val builder = NotificationCompat.Builder(requireActivity(), CHANNEL_OTHERS)
            .setSmallIcon(R.drawable.ic_bedu_logo)
            .setColor(ContextCompat.getColor(requireContext(), R.color.primaryColor))
            .setContentTitle(getString(R.string.purchase_title))
            .setContentText(getString(R.string.purchase_body))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)  //Notification disappears when clicked

        with(NotificationManagerCompat.from(requireActivity()), {
            notify(20, builder.build())
        })
    }

}