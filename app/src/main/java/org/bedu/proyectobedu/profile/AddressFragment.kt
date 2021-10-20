package org.bedu.proyectobedu.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.bedu.proyectobedu.R
import org.bedu.proyectobedu.databinding.FragmentAdressBinding

class AddressFragment : BottomSheetDialogFragment(){

    private var _binding : FragmentAdressBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAdressBinding.inflate(inflater, container, false)
        binding.closeBtn.setOnClickListener{
            dismiss()
        }
        return binding.root
    }

}