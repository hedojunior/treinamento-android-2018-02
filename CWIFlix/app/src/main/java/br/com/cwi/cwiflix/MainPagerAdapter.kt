package br.com.cwi.cwiflix

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import br.com.cwi.cwiflix.fragments.ActorsFragment
import br.com.cwi.cwiflix.fragments.MoviesFragment
import br.com.cwi.cwiflix.fragments.SeriesFragment

class MainPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> MoviesFragment()
            1 -> SeriesFragment()
            else -> ActorsFragment()
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "MOVIES"
            1 -> "SERIES"
            else -> "ACTORS"
        }
    }
}