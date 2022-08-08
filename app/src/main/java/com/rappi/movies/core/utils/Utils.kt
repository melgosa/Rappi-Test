package com.rappi.movies.core.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.ImageView
import com.rappi.movies.presentation.ui.alert.AlertDialogError
import java.io.*
import java.util.*

object Utils {
    var TAG = Utils.javaClass.simpleName

    private const val PATH_POSTER_MOVIES = "poster_movies"
    private const val SUFFIX_JPG = "jpg"


    /**
     * Retorna verdadero si hay conexión a internet, falso en caso contrario
     */
    fun isConnected(mContext: Context): Boolean {
        val cm = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }

    /**
     * Guarda una imagen en el dispositivo, y si ya existe, no la duplica
     */
    fun saveImageToStorage(bitmap: Bitmap?, fileName: String, context: Context) {
        val filename = "$fileName.$SUFFIX_JPG"
        var fos: OutputStream? = null
        val imagesDir = getInnerDir(context)
        val image = File(imagesDir, filename)
        if(!image.exists()){
            fos = FileOutputStream(image)
            fos.use {
                bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, it)
            }
        }
    }

    /**
     * Retorna el path inicial dentro de la carpeta de la app de Movies + poster_movies
     */
    private fun getInnerDir(context: Context): String? {
        val storageDir = File(getDir(context) + PATH_POSTER_MOVIES)
        if (!storageDir.exists()) {
            storageDir.mkdir()
        }
        return getDir(context) + PATH_POSTER_MOVIES
    }

    /**
     * Retorna el path inicial dentro de la carpeta de la app de Movies
     */
    private fun getDir(context: Context): String {
        return (Objects.requireNonNull(context.getExternalFilesDir(null as String?)) as File).absolutePath + "/"
    }

    /**
     * Carga una imagen desde dispositivo
     */
    fun showPosterFromDevice(
        idMovie: String,
        posterImageView: ImageView,
        context: Context
    ) {
        val myBitmap = BitmapFactory.decodeFile(getDir(context) + "$PATH_POSTER_MOVIES/$idMovie.$SUFFIX_JPG")
        posterImageView.setImageBitmap(myBitmap)
    }

    /**
     * Muestra una alerta de error personalizada con un callback para realizar alguna acción
     * según se haya dado tap en reintentar o cancelar
     */
    fun showScreenError(context: Context, retry: (retry: Boolean)-> Unit){
        AlertDialogError.newInstance(context).showMessage(retry)
    }
}
