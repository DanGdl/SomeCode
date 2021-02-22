package com.mdgd.motiv8exam

import androidx.multidex.MultiDexApplication
import com.mdgd.motiv8exam.models.ModelsProvider
import com.mdgd.motiv8exam.models.ModelsProviderImpl

class App : MultiDexApplication() {
    private var provider: ModelsProvider? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
        provider = ModelsProviderImpl(this)
    }

    public fun getProvider() = provider

    companion object {
        var instance: App? = null
            private set
    }
}