package com.mdgd.motiv8exam.models.network

import com.mdgd.motiv8exam.models.dto.Product
import kotlinx.coroutines.flow.Flow

interface Network {
    suspend fun getGroceries(): Flow<Product>
}
