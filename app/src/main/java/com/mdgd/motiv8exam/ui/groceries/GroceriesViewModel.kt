package com.mdgd.motiv8exam.ui.groceries

import android.text.TextUtils
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import com.mdgd.motiv8exam.models.cache.Cache
import com.mdgd.motiv8exam.models.dto.Product
import com.mdgd.motiv8exam.models.network.Network
import com.mdgd.mvi.MviViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class GroceriesViewModel(private val network: Network, private val cache: Cache) :
    MviViewModel<GroceriesScreenState>(), GroceriesContract.ViewModel {

    private val exceptionHandler = CoroutineExceptionHandler { _, e ->
        e.printStackTrace()
        setState(GroceriesScreenState.ShowError(e))
    }
    private val filter = MutableStateFlow("")
    private var observingJob: Job? = null
    private var setupJob: Job? = null

    override fun onObservingChanged() {
        observingJob = if (observingJob == null) {
            viewModelScope.launch(exceptionHandler + Dispatchers.IO) {
                network.getGroceries().receiveAsFlow()
                    .catch { e ->
                        e.printStackTrace()
                        setState(GroceriesScreenState.ShowError(e))
                    }
                    .collect { cache.addProduct(it) }
            }
        } else {
            observingJob?.cancel()
            null
        }
    }

    override fun filter(filterText: String?) {
        viewModelScope.launch {
            filter.emit(filterText ?: "")
        }
    }

    public override fun onAny(owner: LifecycleOwner?, event: Lifecycle.Event) {
        super.onAny(owner, event)
        if (event == Lifecycle.Event.ON_START && setupJob == null) {
            setupJob = viewModelScope.launch(exceptionHandler) {
                cache.getProductsFlow().combine(filter) { list: List<Product>, filterTxt: String ->
                    val filtered = ArrayList(list)
                    if (!TextUtils.isEmpty(filterTxt)) {
                        val weight = filterTxt.toFloat()
                        for (item in list) {
                            if (item.weight.replace("kg", "").toFloat() < weight) {
                                filtered.remove(item)
                            }
                        }
                    }
                    filtered
                }.collect {
                    setState(GroceriesScreenState.SetData(it))
                }
            }
            onObservingChanged()
        }
    }

    override fun getDefaultState(): GroceriesScreenState {
        return GroceriesScreenState.None
    }

    override fun onCleared() {
        setupJob = null
        observingJob = null
        super.onCleared()
    }
}
