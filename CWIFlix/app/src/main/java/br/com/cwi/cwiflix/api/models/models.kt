package br.com.cwi.cwiflix.api.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * @author hedo
 */

open class PaginatedResult(val page: Int,
                           @SerializedName("total_results") val totalResults: Int,
                           @SerializedName("total_pages") val totalPages: Int)

class MediaResult(val results: ArrayList<Media>,
                  page: Int,
                  totalResults: Int,
                  totalPages: Int)
    : PaginatedResult(page, totalResults, totalPages)

class PersonResult(val results: ArrayList<ListPerson>,
                   page: Int,
                   totalResults: Int,
                   totalPages: Int)
    : PaginatedResult(page, totalResults, totalPages)

open class Media(
        val id: Int?,
        @SerializedName("title",
                alternate = ["name"]) val title: String,
        @SerializedName("release_date",
                alternate = ["first_air_date"]) val releaseDate: String,
        @SerializedName("vote_average") val voteAverage: Double,
        @SerializedName("poster_path") val image: String?,
        val overview: String
) : Serializable

class ListPerson(
        val id: Int,
        val name: String,
        @SerializedName("profile_path") val image: String?)


class Movie(
        @SerializedName("original_language") val originalLanguage: String?,
        @SerializedName("original_title") val originalTitle: String?,
        @SerializedName("tagline") val tagline: String?,
        id: Int?,
        title: String,
        releaseDate: String,
        voteAverage: Double,
        image: String?,
        overview: String
) : Serializable, Media(id, title, releaseDate, voteAverage, image, overview)

class Series(
        @SerializedName("original_language") val originalLanguage: String?,
        @SerializedName("tagline") val tagline: String?,
        id: Int?,
        title: String,
        releaseDate: String,
        voteAverage: Double,
        image: String?,
        overview: String
) : Serializable, Media(id, title, releaseDate, voteAverage, image, overview)

class Person(
        @SerializedName("profile_path") val image: String?,
        val name: String,
        val biography: String,
        @SerializedName("place_of_birth") val placeOfBirth: String,
        val birthday: String) : Serializable












