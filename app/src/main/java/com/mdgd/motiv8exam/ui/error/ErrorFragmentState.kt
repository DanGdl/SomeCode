package com.mdgd.motiv8exam.ui.error

import com.mdgd.mvi.ScreenState

sealed class ErrorFragmentState : ScreenState<ErrorContract.View> {
    override fun visit(screen: ErrorContract.View) {}
}
