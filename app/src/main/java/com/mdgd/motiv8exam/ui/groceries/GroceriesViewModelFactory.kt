package com.mdgd.motiv8exam.ui.groceries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mdgd.motiv8exam.App

class GroceriesViewModelFactory() : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass == GroceriesViewModel::class.java) {
            val provider = App.instance!!.getProvider()!!
            GroceriesViewModel(provider.getNetwork(), provider.getCache()) as T
        } else super.create(modelClass)
    }
}
