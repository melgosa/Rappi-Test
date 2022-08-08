package com.rappi.movies.core.extensions

import android.view.View
import java.text.DecimalFormat
import java.util.*

/**
 * Muestra una vista (Widget) en el XML
 */
fun View.show() {
    this.visibility = View.VISIBLE
}

/**
 * Oculta una vista (Widget) en el XML
 * y no deja tampoco el espacio respectivo
 */
fun View.gone() {
    this.visibility = View.GONE
}
