package com.example.emilin.portfolio.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.emilin.portfolio.R
import com.example.emilin.portfolio.base.BaseFragment
import com.google.android.gms.maps.OnMapReadyCallback

class ExperiencesFragment : BaseFragment() {
    override fun onchildHasMapReadyCallBack(): OnMapReadyCallback? {
        return null
    }

    override fun getLayoutResId(): Int = R.layout.fragment_experiences

    fun newInstance(page: Int, title: String): ExperiencesFragment {
        val fragment = ExperiencesFragment()
        val args = Bundle()
        args.putInt(super.PAGE, page)
        args.putString(super.TITLE, title)
        fragment.setArguments(args)
        return fragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}