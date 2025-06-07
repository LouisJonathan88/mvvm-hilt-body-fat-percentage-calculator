// hari tanggal pengerjaan : 29 Mei 2025
// nim                     : 10122362
// nama lengkap            : Louis Jonathan Susanto Putra
// kelas                   : PA 4

package com.example.mvvmhiltbodyfatpercentagecalculator.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvmhiltbodyfatpercentagecalculator.R
import com.example.mvvmhiltbodyfatpercentagecalculator.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, InputFragment())
            .commit()
    }
}