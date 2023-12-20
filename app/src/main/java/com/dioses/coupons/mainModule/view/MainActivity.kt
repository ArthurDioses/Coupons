package com.dioses.coupons.mainModule.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.dioses.coupons.R
import com.dioses.coupons.common.entities.CouponEntity
import com.dioses.coupons.common.utils.hideKeyboard
import com.dioses.coupons.databinding.ActivityMainBinding
import com.dioses.coupons.mainModule.viewModel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupObservers()
        setupButtons()
    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

    private fun setupObservers() {
        mainViewModel.getResult().observe(this) { coupon ->
            if (coupon == null) {
                binding.textInputLayoutDescription.hint = getString(R.string.main_hint_description)
                binding.textInputLayoutDescription.isEnabled = true
                binding.buttonCreate.visibility = View.VISIBLE
            } else {
                binding.editTextDescription.setText(coupon.description)
                val status =
                    getString(if (coupon.isActive) R.string.main_hint_active else R.string.main_hint_description)
                binding.textInputLayoutDescription.hint = status
                binding.textInputLayoutDescription.isEnabled = false
                binding.buttonCreate.visibility = if (coupon.isActive) View.GONE else View.VISIBLE
            }
        }

        mainViewModel.getSnackbarMsg().observe(this) { message ->
            Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun setupButtons() {
        binding.buttonConsult.setOnClickListener {
            mainViewModel.consultCouponByCode(binding.editTextCoupon.text.toString())
            hideKeyboard(this, binding.root)
        }
        binding.buttonCreate.setOnClickListener {
            val coupon = CouponEntity(
                code = binding.editTextCoupon.text.toString(),
                description = binding.editTextDescription.text.toString()
            )
            mainViewModel.saveCoupon(coupon)
            hideKeyboard(this, binding.root)
        }
    }
}