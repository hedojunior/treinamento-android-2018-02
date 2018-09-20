package br.com.cwi.cwiflix.adapters

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import br.com.cwi.cwiflix.R
import br.com.cwi.cwiflix.fragments.ActorsFragment
import br.com.cwi.cwiflix.fragments.MoviesFragment
import br.com.cwi.cwiflix.fragments.SeriesFragment

class MainPagerAdapter(fm: FragmentManager, val context: Context)
    : FragmentPagerAdapter(fm) {

    private val moviesFragment: MoviesFragment by lazy {
        MoviesFragment()
    }

    private val seriesFragment: SeriesFragment by lazy {
        SeriesFragment()
    }

    private val actorsFragment: ActorsFragment by lazy {
        ActorsFragment()
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> moviesFragment
            1 -> seriesFragment
            else -> actorsFragment
        }
    }

    override fun getCount() = 3

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> context.getString(R.string.filmes)
            1 -> context.getString(R.string.series)
            else -> context.getString(R.string.estrelas)
        }
    }
}