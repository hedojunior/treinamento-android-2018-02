package com.example.hedo.projetoexemplo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.view.MenuItem
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)

        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)

        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START)
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var intent = Intent()

        when (item.itemId) {
            R.id.nav_item_1 -> {
                intent = Intent(this, FirstScreenActivity::class.java)
            }

            R.id.nav_item_2 -> {
                Toast.makeText(this,
                        "Item 2",
                        Toast.LENGTH_SHORT).show()
            }

            R.id.nav_item_3 -> {
                Toast.makeText(this,
                        "Item 3",
                        Toast.LENGTH_SHORT).show()
            }
            else -> {
                return false
            }
        }

        drawerLayout.closeDrawers()
        startActivity(intent)
        return true
    }
}
