package com.mdgd.motiv8exam.ui.groceries.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.mdgd.motiv8exam.R
import com.mdgd.motiv8exam.models.dto.Product
import com.mdgd.motiv8exam.ui.adapter.RecyclerAdapter
import com.mdgd.motiv8exam.ui.adapter.RecyclerVH

class ProductsAdapter : RecyclerAdapter<Product>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerVH<Product> {
        return ProductViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        )
    }

    override fun setItems(items: List<Product>) {
        if (this.items.isEmpty()) {
            super.setItems(items)
        } else {
            val oldList = ArrayList(this.items)
            this.items.clear()
            this.items.addAll(items)
            dispatchChanges(oldList, items)
        }
    }

    private class ProductViewHolder(view: View) : RecyclerVH<Product>(view) {
        private val name: TextView = view.findViewById(R.id.product_name)
        private val weight: TextView = view.findViewById(R.id.product_weight)
        private val colorView: View = view.findViewById(R.id.product_color)

        override fun bindItem(item: Product, position: Int) {
            name.text = item.name
            weight.text = item.weight
            colorView.setBackgroundColor(Color.parseColor(item.color))
        }
    }
}
