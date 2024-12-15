package com.mrizkisaputra

import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {
    private var _counter: Int = 0
    val counter get() = _counter

    fun incrementCounter() {
        this._counter++
    }
}