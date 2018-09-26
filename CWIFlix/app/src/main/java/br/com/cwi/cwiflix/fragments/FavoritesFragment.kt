package br.com.cwi.cwiflix.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.cwi.cwiflix.R
import br.com.cwi.cwiflix.adapters.FavoriteAdapter
import br.com.cwi.cwiflix.listeners.OnFavoritesChangeListener
import br.com.cwi.cwiflix.services.FavoritesService
import br.com.cwi.cwiflix.utils.GridSpacingItemDecoration
import br.com.cwi.cwiflix.utils.UserHolder
import kotlinx.android.synthetic.main.fragment_media.*

/**
 * @author hedo
 */
class FavoritesFragment : Fragment(), OnFavoritesChangeListener {
    private val adapter: FavoriteAdapter by lazy {
        mediaRecyclerView.addItemDecoration(GridSpacingItemDecoration(2, 15, false))
        FavoriteAdapter(FavoritesService.favorites)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_media, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FavoritesService.listener = this

        UserHolder.user?.let {
            FavoritesService.initialize(it.uid)
        }
    }

    override fun onFavoriteAdded() {
        mediaRecyclerView.adapter = adapter
    }

    override fun onFavoriteRemoved(index: Int) {
        adapter.notifyItemRemoved(index)
    }
}







