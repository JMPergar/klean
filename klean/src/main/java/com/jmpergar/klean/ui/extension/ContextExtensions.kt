package com.jmpergar.klean.ui.extension

import android.content.Context
import android.support.v4.app.Fragment
import android.widget.Toast

fun Context.showToast(string: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, string, duration).show()
}

fun Fragment.showToast(string: String, duration: Int = Toast.LENGTH_SHORT) {
    context.showToast(string, duration)
}