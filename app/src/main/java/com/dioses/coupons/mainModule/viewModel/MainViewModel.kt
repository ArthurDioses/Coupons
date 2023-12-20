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

    private val result = MutableLiveData<CouponEntity>()
    fun getResult() = result

    private val snackbarMsg = MutableLiveData<Int>()
    fun getSnackbarMsg() = snackbarMsg

    fun consultCouponByCode(code: String) {
        viewModelScope.launch {
            result.value = repository.consultCouponByCode(code)
        }
    }

    fun saveCoupon(couponEntity: CouponEntity) {
        viewModelScope.launch {
            try {
                repository.saveCoupon(couponEntity)
                consultCouponByCode(couponEntity.code)
                snackbarMsg.value = R.string.main_save_success
            } catch (e: Exception) {
                snackbarMsg.value = getMessageErrorByCode(e.message)
            }
        }
    }

}