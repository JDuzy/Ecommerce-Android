package org.bedu.proyectobedu.profile.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.bedu.proyectobedu.databinding.FragmentAdressBinding
import org.bedu.proyectobedu.profile.viewmodel.AddressModelView

class AddressFragment : BottomSheetDialogFragment(){

    private var _binding : FragmentAdressBinding? = null
    private val binding get() = _binding!!
    private val addressModelView: AddressModelView by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addressModelView.init(requireActivity(), requireContext())
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAdressBinding.inflate(inflater, container, false)
        binding.closeBtn.setOnClickListener{
            dismiss()
        }
        addressModelView.gpsLiveData.observe(viewLifecycleOwner,{addressText ->
            binding.actualAdressText.text = addressText
        })

        binding.updateLocation.setOnClickListener {
            addressModelView.getLocation()
            if (addressModelView.gpsLiveData.value == null){
                Toast.makeText(context, "No se ha podido encontrar la ubicación", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }


}