package br.com.cwi.cwiflix

import android.graphics.Movie
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import br.com.cwi.cwiflix.fragments.ActorsFragment
import br.com.cwi.cwiflix.fragments.MoviesFragment
import br.com.cwi.cwiflix.fragments.SeriesFragment
import kotlinx.android.synthetic.main.activity_bottom_navigation.*

class BottomNavigationActivity : AppCompatActivity() {

    var moviesFragment = MoviesFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)

        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigatioListener)
        openFragment(MoviesFragment())
    }

    private val onNavigatioListener = BottomNavigationView.OnNavigationItemSelectedListener {
        when (it.itemId) {
            R.id.movies -> {
                supportActionBar?.title = "MOVIES"
                openFragment(MoviesFragment())
                return@OnNavigationItemSelectedListener true
            }

            R.id.series -> {
                supportActionBar?.title = "SERIES"
                openFragment(SeriesFragment())
                return@OnNavigationItemSelectedListener true
            }

            R.id.actors -> {
                supportActionBar?.title = "ACTORS"
                openFragment(ActorsFragment())
                return@OnNavigationItemSelectedListener true
            }
        }

        false
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.containerFrameLayout, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
