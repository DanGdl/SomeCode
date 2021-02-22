package com.mdgd.motiv8exam.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.mdgd.motiv8exam.R
import com.mdgd.mvi.HostedFragment

class SplashFragment : HostedFragment<SplashContract.View, SplashScreenState, SplashContract.ViewModel, SplashContract.Host>(), SplashContract.View {

    override fun createModel(): SplashContract.ViewModel {
        return ViewModelProvider(this, SplashViewModelFactory()).get(SplashViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun proceedToNextScreen() {
        if (hasHost()) {
            fragmentHost!!.proceedToPokemonsScreen()
        }
    }

    override fun showError(error: Throwable?) {
        if (hasHost()) {
            fragmentHost!!.showError(error)
        }
    }
}
