package br.com.cwi.cwiflix.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import br.com.cwi.cwiflix.activities.MovieActivity
import br.com.cwi.cwiflix.R
import br.com.cwi.cwiflix.adapters.MediaAdapter
import br.com.cwi.cwiflix.api.models.Media
import br.com.cwi.cwiflix.api.models.MediaType
import br.com.cwi.cwiflix.api.models.Movie
import br.com.cwi.cwiflix.listeners.EndlessRecyclerViewScrollListener
import br.com.cwi.cwiflix.presenters.MediaPresenter
import br.com.cwi.cwiflix.views.MediaView

import kotlinx.android.synthetic.main.fragment_media.*
import kotlinx.android.synthetic.main.fragment_media.view.*

class MoviesFragment : Fragment(), MediaView<Movie> {

    companion object {
        private const val TAG = "CWIFlix.MoviesFragment"
    }

    private lateinit var adapter: MediaAdapter

    private val presenter: MediaPresenter<Movie> by lazy {
        MediaPresenter(this, MediaType.MOVIE, 1)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_media, container, false)
        val manager = view.recyclerView.layoutManager as GridLayoutManager

        view.recyclerView.addOnScrollListener(object : EndlessRecyclerViewScrollListener(manager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                presenter.onLoadMoreMedia()
            }
        })

        presenter.getMediaList()

        return view
    }

    override fun onResponse(list: ArrayList<Media>, isFirstFetch: Boolean) {
        if (isFirstFetch) {
            adapter = MediaAdapter(list) { media ->
                media.id?.let {
                    presenter.getMediaDetail(it)
                }
            }

            recyclerView.adapter = adapter
        } else {
            adapter.addItems(list)
        }
    }

    override fun onFailure(throwable: Throwable) {
        throwable.run {
            Log.e(TAG, "MoviesFragment.onFailure: $localizedMessage", this)
        }
    }

    override fun onDetailResponse(media: Movie) {
        val intent = Intent(activity, MovieActivity::class.java)
        intent.putExtra("movie", media)

        activity?.startActivity(intent)
    }

    override fun onDetailFailure(throwable: Throwable) {
        throwable.run {
            Log.e(TAG, "MoviesFragment.onDetailFailure: $localizedMessage", this)
        }
    }

    override fun onLastPageReached() {
        recyclerView.clearOnScrollListeners()
        Toast.makeText(context, "Não há mais filmes para exibir.", Toast.LENGTH_LONG).show()
    }
}














