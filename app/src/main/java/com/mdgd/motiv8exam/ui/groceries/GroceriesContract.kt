package com.mdgd.motiv8exam.ui.groceries

import com.mdgd.motiv8exam.models.dto.Product
import com.mdgd.mvi.FragmentContract

class GroceriesContract {

    interface ViewModel : FragmentContract.ViewModel<GroceriesScreenState> {
        fun onObservingChanged()
        infix fun filter(filterText: String?)
    }

    interface View : FragmentContract.View {
        fun showError(error: Throwable?)
        fun setItems(products: List<Product>)
    }

    interface Host : FragmentContract.Host {
        fun showError(error: Throwable?)
    }
}
