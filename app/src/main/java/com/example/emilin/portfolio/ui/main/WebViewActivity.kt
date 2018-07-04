package com.example.emilin.portfolio.ui.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.emilin.portfolio.R
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        val intent : Intent = intent
        with(intent){
            getStringExtra("url")?.let{
                webview.loadUrl(it)
            }
        }
    }
}
