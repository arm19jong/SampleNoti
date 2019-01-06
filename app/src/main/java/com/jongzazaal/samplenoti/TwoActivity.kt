package com.jongzazaal.samplenoti

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.twolayout.*

class TwoActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.twolayout)
        button.text = intent.getStringExtra("extra")

    }
}