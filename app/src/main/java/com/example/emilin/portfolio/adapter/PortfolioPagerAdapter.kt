package com.example.emilin.portfolio.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.emilin.portfolio.R
import com.example.emilin.portfolio.ui.DiplomaFragment
import com.example.emilin.portfolio.ui.ExperiencesFragment

class PortfolioPagerAdapter (val fm : FragmentManager, val context : Context): FragmentPagerAdapter(fm) {

    var numItem = 2
    private val tabTitles = arrayOf(context.resources.getString(R.string.diploma_fragment_title), context.resources.getString(R.string.experiences_fragment_title))

    override fun getItem(position: Int): Fragment? {
        when (position){
            0 -> return DiplomaFragment().newInstance(1,  context.resources.getString(R.string.diploma_fragment_title))
            1-> return ExperiencesFragment().newInstance(2, context.resources.getString(R.string.experiences_fragment_title))
            else -> {
                return null
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabTitles[position]
    }

    override fun getCount(): Int = numItem
}