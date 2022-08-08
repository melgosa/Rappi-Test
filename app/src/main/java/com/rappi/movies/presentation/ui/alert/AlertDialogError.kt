package com.rappi.movies.presentation.ui.alert

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import com.rappi.movies.R

/**
 * Crea una alerta de error, con una imagen y dos botones, dependiendo
 * del boton elegido, se puede ejecutar una acción que se decide donde
 * se llame por medio del callback
 */
class AlertDialogError(context: Context): Dialog(context)  {

    private lateinit var tvMessage: TextView
    private lateinit var tvTitle: TextView
    private lateinit var ivError: ImageView
    private lateinit var btnBack: MaterialButton
    private lateinit var btnRetry: MaterialButton

    companion object {
        @JvmStatic
        fun newInstance(context: Context): AlertDialogError {
            return AlertDialogError(context)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
        init()
    }

    fun showMessage(
        callback: (retry:Boolean) -> Unit
    ){
        context.setTheme(R.style.NoMarginsDialog)
        if (!isShowing) {
            super.show()

            btnBack.setOnClickListener {
                dismiss()
                callback.invoke(false)
            }

            btnRetry.setOnClickListener {
                dismiss()
                callback.invoke(true)
            }
        }

    }

    private fun init() {
        setContentView(R.layout.alert_dialog_error)
        setCancelable(false)

        tvTitle = findViewById(R.id.ade_tvTitle)
        tvMessage = findViewById(R.id.ade_tvMessage)
        ivError = findViewById(R.id.ade_ivError)
        btnBack = findViewById(R.id.ade_btn_cancel)
        btnRetry = findViewById(R.id.ade_btn_retry)
    }

    override fun dismiss() {
        if (isShowing) {
            super.dismiss()
        }
    }
}