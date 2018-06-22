package com.example.emilin.portfolio.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.emilin.portfolio.app.PortFolio
import com.example.emilin.portfolio.di.component.DaggerFragmentComponent
import com.example.emilin.portfolio.di.component.FragmentComponent
import com.example.emilin.portfolio.di.module.FragmentModule


abstract class BaseFragment : Fragment() {
    protected var PAGE: String? = null
    protected var TITLE: String? = null
    lateinit var component: FragmentComponent

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(getLayoutResId(), container, false)
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            PAGE = it.getString(PAGE)
            TITLE = it.getString(TITLE)
        }
        initInjector()
    }

    abstract fun getLayoutResId() : Int


    private fun initInjector() {
        component = DaggerFragmentComponent.builder().applicationComponent(PortFolio.component).fragmentModule(FragmentModule(this)).build()
    }
}