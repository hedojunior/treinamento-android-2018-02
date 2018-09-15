package br.com.cwi.cwiflix.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.cwi.cwiflix.BuildConfig
import br.com.cwi.cwiflix.R
import br.com.cwi.cwiflix.adapters.MediaAdapter
import br.com.cwi.cwiflix.api.MovieDatabaseService
import br.com.cwi.cwiflix.api.models.MediaResult
import kotlinx.android.synthetic.main.fragment_movies.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesFragment : Fragment(), Callback<MediaResult> {

    lateinit var adapter: MediaAdapter

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        MovieDatabaseService.service.getPopularMovies(BuildConfig.API_KEY).enqueue(this)

        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onFailure(call: Call<MediaResult>, t: Throwable) {
        Log.e("MoviesFragment", t.localizedMessage, t);
    }

    override fun onResponse(call: Call<MediaResult>, response: Response<MediaResult>) {
        response.body()?.results?.let {
            adapter = MediaAdapter(it)
            recyclerView.adapter = adapter
        }
    }

}














