package com.mdgd.motiv8exam.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SplashViewModelFactory() : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass == SplashViewModel::class.java) {
            SplashViewModel() as T
        } else super.create(modelClass)
    }
}
