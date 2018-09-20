package br.com.cwi.cwiflix.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.cwi.cwiflix.activities.MovieActivity
import br.com.cwi.cwiflix.R
import br.com.cwi.cwiflix.adapters.MediaAdapter
import br.com.cwi.cwiflix.api.models.Media
import br.com.cwi.cwiflix.api.models.MediaType
import br.com.cwi.cwiflix.api.models.Movie
import br.com.cwi.cwiflix.presenters.MediaPresenter
import br.com.cwi.cwiflix.views.MediaView

import kotlinx.android.synthetic.main.fragment_media.*

class MoviesFragment : Fragment(), MediaView<Movie> {

    companion object {
        private const val TAG = "CWIFlix.MoviesFragment"
    }

    private lateinit var adapter: MediaAdapter

    private val presenter: MediaPresenter<Movie> by lazy {
        MediaPresenter(this, MediaType.MOVIE)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter.getMediaList()
        return inflater.inflate(R.layout.fragment_media, container, false)
    }

    override fun onResponse(list: List<Media>) {
        adapter = MediaAdapter(list) { media ->
            media.id?.let {
                presenter.getMediaDetail(it)
            }
        }

        recyclerView.adapter = adapter
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
}














