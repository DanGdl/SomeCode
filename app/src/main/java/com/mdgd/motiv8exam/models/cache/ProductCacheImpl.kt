package com.mdgd.motiv8exam.models.cache

import com.mdgd.motiv8exam.models.dto.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class ProductCacheImpl : Cache {
    private val products = MutableStateFlow(ArrayList<Product>())
    private val filter = MutableStateFlow("")

    override suspend fun addProduct(product: Product) {
        val value = ArrayList(products.value)
        value.add(0, product)
        products.emit(value)
    }

    override fun getProductsFlow(): Flow<List<Product>> {
        return products
    }

    override suspend fun putFilter(s: String) {
        filter.emit(s)
    }

    override fun getFilterFlow(): Flow<String> {
        return filter
    }
}