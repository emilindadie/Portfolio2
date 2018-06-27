package com.example.emilin.portfolio.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.emilin.portfolio.R
import com.example.emilin.portfolio.app.PortFolio
import com.example.emilin.portfolio.di.component.DaggerFragmentComponent
import com.example.emilin.portfolio.di.component.FragmentComponent
import com.example.emilin.portfolio.di.module.FragmentModule
import com.google.android.gms.maps.MapFragment
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment


abstract class BaseFragment : Fragment() {
    protected var PAGE: String? = null
    protected var TITLE: String? = null
    lateinit var component: FragmentComponent

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(getLayoutResId(), container, false)
        if(onchildHasMapReadyCallBack() != null){
            val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
            mapFragment.getMapAsync(onchildHasMapReadyCallBack())
        }
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        arguments?.let {
            PAGE = it.getString(PAGE)
            TITLE = it.getString(TITLE)
        }
        initInjector()
    }

    abstract fun getLayoutResId() : Int

    abstract fun onchildHasMapReadyCallBack() : OnMapReadyCallback?


    private fun initInjector() {
        component = DaggerFragmentComponent.builder().applicationComponent(PortFolio.component).fragmentModule(FragmentModule(this)).build()
    }

}