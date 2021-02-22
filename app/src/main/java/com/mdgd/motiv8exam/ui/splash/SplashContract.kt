package com.mdgd.motiv8exam.ui.splash

import com.mdgd.mvi.FragmentContract

class SplashContract {
    companion object {
        const val SPLASH_DELAY = 1000L
    }


    interface ViewModel : FragmentContract.ViewModel<SplashScreenState>

    interface View : FragmentContract.View {
        fun proceedToNextScreen()
        fun showError(error: Throwable?)
    }

    interface Host : FragmentContract.Host {
        fun proceedToPokemonsScreen()
        fun showError(error: Throwable?)
    }
}
