package br.com.cwi.cwiflix.utils

/**
 * @author hedo
 */
object ImageURLProvider {
    private const val BASE_URL = "https://image.tmdb.org/t/p/"
    private const val SMALL_URL = BASE_URL + "w200"
    private const val MEDIUM_URL = BASE_URL + "w400"
    private const val LARGE_URL = BASE_URL + "w500"

    fun small(path: String) = SMALL_URL + path

    fun medium(path: String) = MEDIUM_URL + path

    fun large(path: String) = LARGE_URL + path
}