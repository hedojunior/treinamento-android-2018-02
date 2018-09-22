package br.com.cwi.cwiflix.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import br.com.cwi.cwiflix.R
import br.com.cwi.cwiflix.adapters.MainPagerAdapter
import br.com.cwi.cwiflix.utils.UserHolder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentPagerAdapter =
                MainPagerAdapter(supportFragmentManager, this)

        mainViewPager.offscreenPageLimit = 3
        mainViewPager.adapter = fragmentPagerAdapter

        tabLayout.setupWithViewPager(mainViewPager)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.logout) {
            UserHolder.logOut(this)
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        return super.onOptionsItemSelected(item)
    }
}












