package br.com.cwi.cwiflix.api

import br.com.cwi.cwiflix.api.models.MediaResult
import br.com.cwi.cwiflix.api.models.PersonResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author hedo
 */
interface IMovieDatabaseService {

    @GET("movie/popular")
    fun getPopularMovies(): Call<MediaResult>

    @GET("tv/popular")
    fun getPopularTVShows(): Call<MediaResult>

    @GET("person/popular")
    fun getPopularPeople(): Call<PersonResult>
}







