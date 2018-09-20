package br.com.cwi.cwiflix.presenters

import br.com.cwi.cwiflix.api.MovieDatabaseService
import br.com.cwi.cwiflix.api.models.*
import br.com.cwi.cwiflix.views.MediaView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @author hedo
 */
class MediaPresenter<T : Media>(val view: MediaView<T>, private val mediaType: MediaType) : Callback<MediaResult> {

    fun getMediaList() {
        when (mediaType) {
            MediaType.MOVIE -> MovieDatabaseService.service.getPopularMovies().enqueue(this)
            MediaType.TV_SHOW -> MovieDatabaseService.service.getPopularTVShows().enqueue(this)
        }
    }

    fun getMediaDetail(id: Int) {
        val request: Call<T> = when (mediaType) {
            MediaType.MOVIE ->
                MovieDatabaseService.service.getMovieDetail(id)
            MediaType.TV_SHOW ->
                MovieDatabaseService.service.getSeriesDetail(id)

        } as Call<T>

        request.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>?, response: Response<T>?) {
                response?.body()?.let {
                    view.onDetailResponse(it)
                }
            }

            override fun onFailure(call: Call<T>?, t: Throwable?) {
                t?.run { view.onDetailFailure(this) }
            }
        })
    }

    override fun onResponse(call: Call<MediaResult>?, response: Response<MediaResult>?) {
        response?.body()?.results?.let {
            view.onResponse(it)
        }
    }

    override fun onFailure(call: Call<MediaResult>?, t: Throwable?) {
        t?.run { view.onFailure(this) }
    }
}
