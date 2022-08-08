package com.rappi.movies.presentation.ui.alert

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import com.rappi.movies.R


/**
 * Nos ayuda a mostrar alertas pequeñas
 */
class AlertDialog(context: Context) : Dialog(context) {

    private lateinit var tvMessage: TextView
    private lateinit var tvTitle: TextView

    private lateinit var mbAcceptOnly: MaterialButton

    companion object {
        @JvmStatic
        fun newInstance(context: Context): AlertDialog {
            return AlertDialog(context)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context.setTheme(R.style.DialogTheme)
        window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
        init()
    }

    private fun init() {
        setContentView(R.layout.alert_dialog_message)
        setCancelable(false)
        tvTitle = findViewById(R.id.adm_tvTitle)
        tvMessage = findViewById(R.id.adm_tvMessage)

        mbAcceptOnly = findViewById(R.id.adm_mbAcceptOnly)
    }

    fun showMessage() {
        if (!isShowing) {
            super.show()

            mbAcceptOnly.setOnClickListener { dismiss() }
        }
    }

    override fun dismiss() {
        if (isShowing) {
            super.dismiss()
        }
    }
}