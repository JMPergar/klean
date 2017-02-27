package com.lorcapp.klean.ui

import android.app.Activity
import android.support.v4.app.Fragment
import android.widget.Toast

fun Fragment.showToast(string: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, string, duration).show()
}

fun Activity.showToast(string: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, string, duration).show()
}