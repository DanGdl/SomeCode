package com.mdgd.motiv8exam.ui.groceries

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.ToggleButton
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mdgd.motiv8exam.R
import com.mdgd.motiv8exam.models.dto.Product
import com.mdgd.motiv8exam.ui.groceries.adapter.ProductsAdapter
import com.mdgd.motiv8exam.util.TextWatcherImpl
import com.mdgd.mvi.HostedFragment

class GroceriesFragment :
    HostedFragment<GroceriesContract.View, GroceriesScreenState, GroceriesContract.ViewModel, GroceriesContract.Host>(),
    GroceriesContract.View,
    CompoundButton.OnCheckedChangeListener {

    private val adapter = ProductsAdapter()
    private var recycler: RecyclerView? = null

    override fun createModel(): GroceriesContract.ViewModel {
        return ViewModelProvider(
            this,
            GroceriesViewModelFactory()
        ).get(GroceriesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_groceries, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<ToggleButton>(R.id.start_stop).setOnCheckedChangeListener(this)
        view.findViewById<EditText>(R.id.filter_input)
            .addTextChangedListener(object : TextWatcherImpl() {
                override fun afterTextChanged(s: Editable?) {
                    model!! filter (s?.toString()?.trim())
                }
            })
        recycler = view.findViewById(R.id.products)
        recycler?.layoutManager = LinearLayoutManager(requireContext())
        recycler?.adapter = adapter
    }

    override fun showError(error: Throwable?) {
        if (hasHost()) {
            fragmentHost!!.showError(error)
        }
    }

    override fun setItems(products: List<Product>) {
        adapter.setItems(products)
        recycler?.scrollToPosition(0)
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        model!!.onObservingChanged()
    }
}
