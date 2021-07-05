package com.mdgd.motiv8exam.ui.adapter

sealed class ClickEvent<T> {

    class EmptyData<T>() : ClickEvent<T>()

    class ClickData<T>(val data: T) : ClickEvent<T>()
}
