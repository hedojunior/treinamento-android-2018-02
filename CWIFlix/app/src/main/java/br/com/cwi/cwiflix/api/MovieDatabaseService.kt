package br.com.cwi.cwiflix.api

import br.com.cwi.cwiflix.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author hedo
 */
object MovieDatabaseService {
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    val service: IMovieDatabaseService = createService()

    private fun createService(): IMovieDatabaseService {

        val client = OkHttpClient.Builder()
                .addInterceptor {
                    var request = it.request()

                    var url = request.url()

                    url = url.newBuilder()
                            .addQueryParameter("api_key", BuildConfig.API_KEY)
                            .addQueryParameter("language", BuildConfig.LANGUAGE)
                            .build()

                    request = request.newBuilder().url(url).build()

                    it.proceed(request)
                }
                .build()

        val retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return retrofit.create(IMovieDatabaseService::class.java)
    }
}



















