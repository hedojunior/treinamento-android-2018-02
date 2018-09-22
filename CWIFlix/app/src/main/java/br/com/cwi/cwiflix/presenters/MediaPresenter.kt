package br.com.cwi.cwiflix.presenters

import br.com.cwi.cwiflix.services.api.MovieDatabaseService
import br.com.cwi.cwiflix.services.api.models.*
import br.com.cwi.cwiflix.views.MediaView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @author hedo
 */
class MediaPresenter<T : Media>(val view: MediaView<T>, private val mediaType: MediaType, private var page: Int) : Callback<MediaResult> {

    private var lastPage: Int = 0

    fun getMediaList() {
        requestMedia()
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
        response?.body()?.let {
            lastPage = 4 //it.totalPages
            view.onResponse(it.results, page == 1)
        }
    }

    override fun onFailure(call: Call<MediaResult>?, t: Throwable?) {
        t?.run { view.onFailure(this) }
    }

    fun onLoadMoreMedia() {
        page++
        if (page <= lastPage) {
            requestMedia()
        } else {
            view.onLastPageReached()
        }
    }

    private fun requestMedia() {
        when (mediaType) {
            MediaType.MOVIE -> MovieDatabaseService.service.getPopularMovies(page).enqueue(this)
            MediaType.TV_SHOW -> MovieDatabaseService.service.getPopularTVShows(page).enqueue(this)
        }
    }
}
