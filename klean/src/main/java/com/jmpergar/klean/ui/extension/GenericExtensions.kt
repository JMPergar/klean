package com.jmpergar.klean.ui.extension

inline fun consume(f: () -> Unit): Boolean {
    f()
    return true
}