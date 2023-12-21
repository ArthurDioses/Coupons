package com.dioses.coupons.mainModule.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.dioses.coupons.BR
import com.dioses.coupons.R
import com.dioses.coupons.common.utils.hideKeyboard
import com.dioses.coupons.databinding.ActivityMainBinding
import com.dioses.coupons.mainModule.viewModel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupViewModel()
        setupObservers()
    }

    private fun setupViewModel() {
        val viewModel: MainViewModel by viewModels()
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel,viewModel)
    }

    private fun setupObservers() {
        binding.viewModel?.let {
            it.coupon.observe(this@MainActivity) { coupon ->
                binding.isActive = coupon != null && coupon.isActive
            }
            it.getSnackbarMsg().observe(this@MainActivity) { message ->
                Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
            }
            it.isHideKeyboard().observe(this@MainActivity) { isHide ->
                if (isHide) {
                    hideKeyboard(this@MainActivity, binding.root)
                }
            }
        }
    }
}