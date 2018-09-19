package br.com.cwi.cwiflix.presenters

import android.content.Intent
import android.util.Log
import br.com.cwi.cwiflix.activities.MovieActivity
import br.com.cwi.cwiflix.api.MovieDatabaseService
import br.com.cwi.cwiflix.api.models.MediaResult
import br.com.cwi.cwiflix.api.models.Movie
import br.com.cwi.cwiflix.fragments.MoviesView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesPresenter(val view: MoviesView): Callback<MediaResult> {

    fun onCreaView() {
        MovieDatabaseService.service.getPopularMovies().enqueue(this)
    }

    override fun onFailure(call: Call<MediaResult>?, t: Throwable?) {
        Log.e("MoviesFragment", t?.localizedMessage, t)
    }

    override fun onResponse(call: Call<MediaResult>?, response: Response<MediaResult>?) {
        response?.body()?.results?.let {
            view.onResponse(it)
        }
    }

    fun getMovieDetail(id: Int) {
        val request = MovieDatabaseService.service.getMovieDetail(id)

        request.enqueue(object : Callback<Movie> {

            override fun onResponse(call: Call<Movie>?, response: Response<Movie>?) {
                response?.body()?.let {
                    view.goToDetail(it)
                }
            }

            override fun onFailure(call: Call<Movie>?, t: Throwable?) {
                Log.e("rsponse", t.toString())
            }
        })
    }
}