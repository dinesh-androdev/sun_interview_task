package com.sunnetwork

import android.content.Context
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    /**
     * To start required default functionality when entering into this Screen
     */
    abstract fun start(context: Context)

}
