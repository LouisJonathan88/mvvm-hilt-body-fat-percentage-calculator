package com.example.mvvmhiltbodyfatpercentagecalculator.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.mvvmhiltbodyfatpercentagecalculator.databinding.FragmentOutputBinding
import com.example.mvvmhiltbodyfatpercentagecalculator.viewmodel.BfpViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OutputFragment : Fragment() {

    private lateinit var binding: FragmentOutputBinding

    // Ganti ViewModel ke BfpViewModel
    private val viewModel: BfpViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOutputBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observasi LiveData dari ViewModel
        viewModel.bfpData.observe(viewLifecycleOwner) { bfpData ->
            binding.nilaiBmi.text = "Nilai BMI Anda: %.2f".format(bfpData.bmiValue)
            binding.kategoriBmi.text = "Kategori BMI: ${bfpData.category}"
            binding.nilaiBfp.text = "Nilai BFP Anda: %.2f".format(bfpData.bfpValue)
        }

        // Tombol info developer
        binding.btnInfoDeveloper.setOnClickListener {
            val infoDeveloperFragment = InfoDeveloperFragment()
            infoDeveloperFragment.show(parentFragmentManager, "InfoDeveloper")
        }
    }
}
