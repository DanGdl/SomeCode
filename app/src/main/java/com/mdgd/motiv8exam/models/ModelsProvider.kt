package com.mdgd.motiv8exam.models

import com.mdgd.motiv8exam.models.cache.Cache
import com.mdgd.motiv8exam.models.network.Network

interface ModelsProvider {
    fun getNetwork(): Network
    fun getCache(): Cache
}
