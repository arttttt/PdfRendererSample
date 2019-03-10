package com.arttttt.pdfviewerdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.arttttt.pdfviewerdemo.custom.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager.adapter = ViewPagerAdapter(supportFragmentManager, this, "sample.pdf")
    }
}
