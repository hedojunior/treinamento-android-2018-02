package br.com.cwi.cwiflix.fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import br.com.cwi.cwiflix.BuildConfig
import br.com.cwi.cwiflix.R
import br.com.cwi.cwiflix.activities.MovieActivity
import br.com.cwi.cwiflix.activities.SeriesActivity
import br.com.cwi.cwiflix.adapters.MediaAdapter
import br.com.cwi.cwiflix.api.MovieDatabaseService
import br.com.cwi.cwiflix.api.models.*
import br.com.cwi.cwiflix.presenters.MediaPresenter
import br.com.cwi.cwiflix.views.MediaView
import kotlinx.android.synthetic.main.fragment_media.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SeriesFragment : Fragment(), MediaView<Series> {

    companion object {
        private const val TAG = "CWIFlix.SeriesFragment"
    }

    private lateinit var adapter: MediaAdapter

    private val presenter: MediaPresenter<Series> by lazy {
        MediaPresenter(this, MediaType.TV_SHOW)
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
}














