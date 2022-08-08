package com.rappi.movies.presentation.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * Clase base para un Fragment
 *
 * @param Binding ViewBinding del Layout del Fragment
 */
abstract class BaseFragment<Binding: ViewBinding>: Fragment() {

    protected var _binding: Binding? = null
    protected lateinit var mView: View
    protected lateinit var mContext: Context
    protected val  binding get() = _binding!!
    //protected lateinit var mViewModel: ZICIPNCollaboratorsViewModel
/*    protected lateinit var mViewModel: ZKFTeamTrainerViewModel
    protected lateinit var mLoader: ZKFAnimatedLoader*/

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = this.setupFragmentView(inflater, container, savedInstanceState)
        //mViewModel =  ViewModelProvider(requireActivity()).get(ZICIPNCollaboratorsViewModel::class.java)
        mContext = mView.context
        //mLoader = ZKFAnimatedLoader(mContext)
        initElements()
        return mView
    }

    /**
     * Metodo para inflar y obetener la vista del Fragment
     *
     * @return Vista inflada del Fragment
     */
    abstract fun setupFragmentView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View

    /**
     * Devuelve el layout principal que contiene los elementos del Fragment
     *
     * @return Layout principal del fragment
     */
    protected abstract fun getViewContainer(): View?

    /**
     * Inicializa los elementos del Fragment una vez creada la Vista
     */
    protected abstract fun initElements()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}