package br.com.cwi.cwiflix.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author hedo
 */
object MovieDatabaseService {
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    val service: IMovieDatabaseService = createService()

    private fun createService(): IMovieDatabaseService {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return retrofit.create(IMovieDatabaseService::class.java)
    }
}



















