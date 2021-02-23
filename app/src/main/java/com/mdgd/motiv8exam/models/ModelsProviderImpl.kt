package com.mdgd.motiv8exam.models

import android.content.Context
import com.mdgd.motiv8exam.models.cache.Cache
import com.mdgd.motiv8exam.models.cache.ProductCacheImpl
import com.mdgd.motiv8exam.models.network.Network
import com.mdgd.motiv8exam.models.network.NetworkImpl

class ModelsProviderImpl(private val appCtx: Context) : ModelsProvider {
    private val cache = ProductCacheImpl()

    override fun getCache(): Cache {
        return cache
    }

    override fun getNetwork(): Network {
        return NetworkImpl()
    }
}