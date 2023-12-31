package com.dioses.coupons.mainModule.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dioses.coupons.R
import com.dioses.coupons.common.entities.CouponEntity
import com.dioses.coupons.common.utils.getMessageErrorByCode
import com.dioses.coupons.mainModule.model.MainRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val repository = MainRepository()

    val coupon = MutableLiveData(CouponEntity())

    private val hideKeyboard = MutableLiveData<Boolean>()
    fun isHideKeyboard() = hideKeyboard

    private val snackbarMsg = MutableLiveData<Int>()
    fun getSnackbarMsg() = snackbarMsg

    fun consultCouponByCode() {
        coupon.value?.code?.let { code ->
            viewModelScope.launch {
                hideKeyboard.value = true
                coupon.value = repository.consultCouponByCode(code) ?: CouponEntity(
                    code = code,
                    isActive = false
                )
            }
        }
    }

    fun saveCoupon() {
        coupon.value?.let { couponEntity ->
            viewModelScope.launch {
                hideKeyboard.value = true
                try {
                    couponEntity.isActive = true
                    repository.saveCoupon(couponEntity)
                    consultCouponByCode()
                    snackbarMsg.value = R.string.main_save_success
                } catch (e: Exception) {
                    snackbarMsg.value = getMessageErrorByCode(e.message)
                }
            }
        }
    }

}