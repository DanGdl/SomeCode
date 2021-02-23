package com.mdgd.motiv8exam.models.network

import com.mdgd.motiv8exam.models.dto.Product
import kotlinx.coroutines.channels.Channel

interface Network {
    suspend fun getGroceries(): Channel<Product>
}