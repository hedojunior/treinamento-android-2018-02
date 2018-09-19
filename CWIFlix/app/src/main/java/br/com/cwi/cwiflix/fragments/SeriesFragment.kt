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
import br.com.cwi.cwiflix.api.models.MediaResult
import br.com.cwi.cwiflix.api.models.Movie
import br.com.cwi.cwiflix.api.models.Series
import kotlinx.android.synthetic.main.fragment_media.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SeriesFragment : Fragment(), Callback<MediaResult> {
    lateinit var adapter: MediaAdapter

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        MovieDatabaseService.service.getPopularTVShows().enqueue(this)

        return inflater.inflate(R.layout.fragment_media, container, false)
    }

    override fun onFailure(call: Call<MediaResult>, t: Throwable) {
        Log.e("SeriesFragment", t.localizedMessage, t);
    }

    override fun onResponse(call: Call<MediaResult>, response: Response<MediaResult>) {
        response.body()?.results?.let {

            adapter = MediaAdapter(it) { media ->
                media.id?.let {
                    getSeriesDetail(media.id)
                }
            }

            recyclerView.adapter = adapter
        }
    }

    private fun getSeriesDetail(id: Int) {
        val request = MovieDatabaseService.service.getSeriesDetail(id)

        request.enqueue(object : Callback<Series> {

            override fun onResponse(call: Call<Series>?, response: Response<Series>?) {

                val intent = Intent(activity, SeriesActivity::class.java)
                intent.putExtra("series", response?.body())

                activity?.startActivity(intent)
            }

            override fun onFailure(call: Call<Series>?, t: Throwable?) {
                Log.e("rsponse", t.toString())
            }
        })
    }
}














