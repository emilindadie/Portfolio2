package com.example.emilin.portfolio.ui

import android.os.Bundle
import android.view.View
import com.example.emilin.portfolio.R
import com.example.emilin.portfolio.base.BaseFragment

class ExperiencesFragment : BaseFragment() {
    override fun getLayoutResId(): Int = R.layout.fragment_experiences

    fun newInstance(page: Int, title: String): ExperiencesFragment {
        val fragment = ExperiencesFragment()
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