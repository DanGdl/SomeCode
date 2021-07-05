package com.mdgd.motiv8exam.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mdgd.motiv8exam.R
import com.mdgd.motiv8exam.ui.error.ErrorFragment
import com.mdgd.motiv8exam.ui.groceries.GroceriesContract

class MainActivity : AppCompatActivity(), GroceriesContract.Host {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun showError(error: Throwable?) {
        if (supportFragmentManager.findFragmentByTag("error") == null) {
            val errorFragment = ErrorFragment.newInstance(
                R.string.dialog_error_title,
                R.string.dialog_error_message
            )
            errorFragment.setError(error)
            errorFragment.show(supportFragmentManager, "error")
        }
    }
}
