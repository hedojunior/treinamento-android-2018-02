package br.com.cwi.cwiflix.api

import br.com.cwi.cwiflix.api.models.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
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

    @GET("movie/{movie_id}")
    fun getMovieDetail(@Path("movie_id") id: Int) : Call<Movie>

    @GET("person/{person_id}")
    fun getPersonDetail(@Path("person_id") id: Int) : Call<Person>

    @GET("tv/{tv_id}")
    fun getSeriesDetail(@Path("tv_id") id: Int) : Call<Series>
}







