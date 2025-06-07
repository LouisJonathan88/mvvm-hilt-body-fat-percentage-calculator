package com.example.mvvmhiltbodyfatpercentagecalculator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmhiltbodyfatpercentagecalculator.model.BfpData
import com.example.mvvmhiltbodyfatpercentagecalculator.repository.BfpRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class BfpViewModel  @Inject constructor(
    private val bfpRepository: BfpRepository
) : ViewModel() {
    private var _bfpData = MutableLiveData<BfpData>()
    val bfpData: LiveData<BfpData> = _bfpData

    fun calculateBfp(weight: Double, height: Double, age: Int, isMale: Boolean) {
        val result = bfpRepository.calculateBfp(weight, height, age, isMale)
        _bfpData.value = result
    }
}