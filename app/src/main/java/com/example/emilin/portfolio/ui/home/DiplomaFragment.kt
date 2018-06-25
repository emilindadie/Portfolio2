package com.example.emilin.portfolio.ui.home


import android.os.Bundle
import android.view.View

import com.example.emilin.portfolio.R
import com.example.emilin.portfolio.base.BaseFragment

class DiplomaFragment : BaseFragment() {
    override fun getLayoutResId(): Int = R.layout.fragment_diploma

    fun newInstance(page: Int, title: String): DiplomaFragment {
        val fragment = DiplomaFragment()
        val args = Bundle()
        args.putInt(super.PAGE, page)
        args.putString(super.TITLE, title)
        fragment.setArguments(args)
        return fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
