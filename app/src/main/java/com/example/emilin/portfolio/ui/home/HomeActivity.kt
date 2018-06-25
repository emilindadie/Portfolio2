package com.example.emilin.portfolio.ui.home

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import com.example.emilin.portfolio.R
import com.example.emilin.portfolio.adapter.PortfolioPagerAdapter
import com.example.emilin.portfolio.base.BaseActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity() {
    internal lateinit var tabLayoutAdapter: PortfolioPagerAdapter
    //Inflate target layout thanks to his super class
    override fun getLayoutResId(): Int = R.layout.activity_home

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Recuperation du view pager dans le layout avec son id
        tabLayoutAdapter = PortfolioPagerAdapter(supportFragmentManager, this)

        //assamblage du notre view pager et de notre table layout(pour changer de vues)
        viewPager.setAdapter(tabLayoutAdapter)

        //affichage de la premier vue
        viewPager.getCurrentItem()

        //Evenement de changement de pages
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                viewPager.setCurrentItem(position)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })

        // Give the TabLayout the ViewPager
        val tabLayout = findViewById(R.id.sliding_tabs) as TabLayout
        tabLayout.setupWithViewPager(viewPager)

    }
}