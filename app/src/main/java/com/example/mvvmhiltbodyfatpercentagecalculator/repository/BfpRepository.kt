package com.example.mvvmhiltbodyfatpercentagecalculator.repository

import com.example.mvvmhiltbodyfatpercentagecalculator.model.BfpData
import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlin.math.pow

@Singleton
class BfpRepository @Inject constructor() {

    fun calculateBfp(weight: Double, height: Double, age: Int, isMale: Boolean): BfpData {
        val heightInMeters = height / 100
        val bmi = weight / heightInMeters.pow(2)

        val bfp = if (isMale) {
            (1.20 * bmi) + (0.23 * age) - 16.2
        } else {
            (1.20 * bmi) + (0.23 * age) - 5.4
        }

        val category = when {
            bfp < 10 -> "< 10% (Sangat Rendah)"
            bfp in 10.0..20.0 -> "10-20% (Rendah)"
            bfp in 21.0..25.0 -> "21-25% (Normal)"
            bfp in 26.0..30.0 -> "26-30% (Tinggi)"
            else -> " > 30% (Sangat Tinggi)"
        }

        return BfpData(bmiValue = bmi, bfpValue = bfp, category = category)
    }
}
