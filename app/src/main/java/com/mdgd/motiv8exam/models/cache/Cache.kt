package com.mdgd.motiv8exam.models.cache

import com.mdgd.motiv8exam.models.dto.Product
import kotlinx.coroutines.flow.Flow

interface Cache {
    suspend fun addProduct(product: Product)
    fun getProductsFlow(): Flow<List<Product>>

    suspend fun putFilter(s: String)
    fun getFilterFlow(): Flow<String>
}