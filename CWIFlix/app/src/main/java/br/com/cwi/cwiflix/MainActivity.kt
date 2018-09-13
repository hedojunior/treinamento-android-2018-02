package br.com.cwi.cwiflix

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentPagerAdapter = MainPagerAdapter(supportFragmentManager)
        mainViewPager.adapter = fragmentPagerAdapter

        tabLayout.setupWithViewPager(mainViewPager)
    }
}
