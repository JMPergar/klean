package com.jmpergar.klean.ui.extension

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.loadUrl(url: String) {
    Picasso.with(context).load(url).into(this)
}
