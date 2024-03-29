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
import br.com.cwi.cwiflix.R
import br.com.cwi.cwiflix.activities.SeriesActivity
import br.com.cwi.cwiflix.adapters.MediaAdapter
import br.com.cwi.cwiflix.services.api.models.*
import br.com.cwi.cwiflix.listeners.EndlessRecyclerViewScrollListener
import br.com.cwi.cwiflix.presenters.MediaPresenter
import br.com.cwi.cwiflix.utils.GridSpacingItemDecoration
import br.com.cwi.cwiflix.views.MediaView
import kotlinx.android.synthetic.main.fragment_media.*
import kotlinx.android.synthetic.main.fragment_media.view.*


class SeriesFragment : Fragment(), MediaView<Series> {

    companion object {
        private const val TAG = "CWIFlix.SeriesFragment"
    }

    private lateinit var adapter: MediaAdapter

    private val presenter: MediaPresenter<Series> by lazy {
        MediaPresenter(this, MediaType.TV_SHOW, 1)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_media, container, false)
        val manager = view.mediaRecyclerView.layoutManager as GridLayoutManager

        view.mediaRecyclerView.addOnScrollListener(object : EndlessRecyclerViewScrollListener(manager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                presenter.onLoadMoreMedia()
            }
        })

        view.mediaRecyclerView.addItemDecoration(GridSpacingItemDecoration(2, 15, false))

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

            mediaRecyclerView.adapter = adapter
        } else {
            adapter.addItems(list)
        }
    }

    override fun onFailure(throwable: Throwable) {
        throwable.run {
            Log.e(TAG, "SeriesFragment.onFailure: $localizedMessage", this)
        }
    }

    override fun onDetailResponse(media: Series) {
        val intent = Intent(activity, SeriesActivity::class.java)
        intent.putExtra("series", media)

        activity?.startActivity(intent)
    }

    override fun onDetailFailure(throwable: Throwable) {
        throwable.run {
            Log.e(TAG, "SeriesFragment.onDetailFailure: $localizedMessage", this)
        }
    }

    override fun onLastPageReached() {
        mediaRecyclerView.clearOnScrollListeners()
        Toast.makeText(context, "Não há mais séries para exibir.", Toast.LENGTH_LONG).show()
    }
}














