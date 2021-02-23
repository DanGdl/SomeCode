package com.mdgd.motiv8exam.ui.groceries

import com.mdgd.motiv8exam.models.dto.Product
import com.mdgd.mvi.ScreenState

sealed class GroceriesScreenState : ScreenState<GroceriesContract.View> {

    class ShowError(val e: Throwable) : GroceriesScreenState() {

        override fun visit(screen: GroceriesContract.View) {
            screen.showError(e)
        }
    }

    class SetData(val products: List<Product>) : GroceriesScreenState() {
        override fun visit(screen: GroceriesContract.View) {
            screen.setItems(products)
        }
    }

    object None : GroceriesScreenState()

    override fun visit(screen: GroceriesContract.View) {}
}
