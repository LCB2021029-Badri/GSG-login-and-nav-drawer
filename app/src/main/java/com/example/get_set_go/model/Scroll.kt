package com.example.get_set_go.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Scroll(@StringRes val stringResourceId:Int, @DrawableRes val imageResourceId:Int) {
    // just a class to represent format of objects in recycler view
}
