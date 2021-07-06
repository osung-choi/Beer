package com.osung.beer.view.utils

import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


object BindingAdapter {
    @JvmStatic
    @BindingAdapter("app:setBeerImage")
    fun setBeerImage(imageView: ImageView, imageUrl: String?) {
        imageUrl?.let {
            Glide.with(imageView)
                .load(it)
                .centerInside()
                .into(imageView)
        }
    }

    @JvmStatic
    @BindingAdapter("app:onEditorSearchActionListener")
    fun setOnEditorSearchActionListener(editText: EditText, listener: () -> Unit) {
        editText.setOnEditorActionListener { _, actionId, _ ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH) {
                listener.invoke()
                true

            }else false
        }
    }

}