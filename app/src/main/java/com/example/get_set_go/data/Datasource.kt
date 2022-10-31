package com.example.get_set_go.data

import com.example.get_set_go.R
import com.example.get_set_go.model.Scroll

class Datasource {
    fun loadScroll(): List<Scroll>{
        return listOf<Scroll>(
            Scroll(R.string.scroll1,R.drawable.login_logo),
            Scroll(R.string.scroll2,R.drawable.login_logo),
            Scroll(R.string.scroll3,R.drawable.login_logo),
            Scroll(R.string.scroll4,R.drawable.login_logo),
            Scroll(R.string.scroll5,R.drawable.login_logo),
            Scroll(R.string.scroll6,R.drawable.login_logo),
            Scroll(R.string.scroll7,R.drawable.login_logo),
            Scroll(R.string.scroll8,R.drawable.login_logo),
            Scroll(R.string.scroll9,R.drawable.login_logo),
            Scroll(R.string.scroll10,R.drawable.login_logo),
        )
    }
}