package com.dioses.coupons.common.adapters

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.dioses.coupons.R

@BindingAdapter("isGone")
fun bindingIsGone(view: View, isGone: Boolean) {
    view.visibility = if (isGone) View.GONE else View.VISIBLE
}

@BindingAdapter("textTitle")
fun bindingTextTitle(textView: TextView, isActive: Boolean) {
    if (isActive) {
        textView.setText(R.string.main_text_title)
        textView.setTextColor(Color.BLACK)
    } else {
        textView.setText(R.string.main_text_title_add_coupon)
        textView.setTextColor(Color.YELLOW)
    }
}
