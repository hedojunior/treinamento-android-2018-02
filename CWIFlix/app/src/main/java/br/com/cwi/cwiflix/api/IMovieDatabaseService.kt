package br.com.cwi.cwiflix.api

import br.com.cwi.cwiflix.api.models.MediaResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author hedo
 */
interface IMovieDatabaseService {

    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") key: String)
        : Call<MediaResult>

    @GET("tv/popular")
    fun getPopularTVShows(@Query("api_key") key: String)
        : Call<MediaResult>

    @GET("person/popular")
    fun getPopularPeople(@Query("api_key") key: String)
}







