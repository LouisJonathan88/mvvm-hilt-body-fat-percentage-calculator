package com.example.mvvmhiltbodyfatpercentagecalculator.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.mvvmhiltbodyfatpercentagecalculator.R
import com.example.mvvmhiltbodyfatpercentagecalculator.databinding.FragmentInputBinding
import com.example.mvvmhiltbodyfatpercentagecalculator.viewmodel.BfpViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InputFragment : Fragment() {

    private lateinit var binding: FragmentInputBinding
    private val viewModel: BfpViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInputBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnHitungBmi.setOnClickListener {
            val weightStr = binding.edtBeratBadan.text.toString()
            val heightStr = binding.edtTinggiBadan.text.toString()
            val ageStr = binding.edtUsia.text.toString()

            val isMale = binding.radLakilaki.isChecked
            val isFemale = binding.radPerempuan.isChecked

            if (weightStr.isNotEmpty() && heightStr.isNotEmpty() && ageStr.isNotEmpty() && (isMale || isFemale)) {
                val weight = weightStr.toDoubleOrNull()
                val height = heightStr.toDoubleOrNull()
                val age = ageStr.toIntOrNull()

                if (weight != null && height != null && height > 0 && age != null && age > 0) {
                    viewModel.calculateBfp(weight, height, age, isMale)

                    // Navigasi ke OutputFragment
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, OutputFragment())
                        .addToBackStack(null)
                        .commit()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Masukkan data yang valid (angka positif)",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    requireContext(),
                    "Isi semua kolom dan pilih gender terlebih dahulu",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
