package br.com.cwi.cwiflix.api.models

import com.google.gson.annotations.SerializedName

/**
 * @author hedo
 */

class MediaResult(val results: List<Media>)

class PersonResult(val results: List<ListPerson>)

class Media(
        val id: Int?,
        @SerializedName("title",
                alternate = ["name"]) val title: String,
        @SerializedName("release_date",
                alternate = ["first_air_date"]) val releaseDate: String,
        @SerializedName("vote_average") val voteAverage: Double,
        @SerializedName("poster_path") val image: String,
        val overview: String
)

class ListPerson(
        val id: Int?,
        val name: String?,
        @SerializedName("profile_path") val image: String?)
