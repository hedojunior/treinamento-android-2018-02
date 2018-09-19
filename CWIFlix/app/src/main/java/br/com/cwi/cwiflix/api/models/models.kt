package br.com.cwi.cwiflix.api.models

import android.os.Bundle
import com.google.gson.annotations.SerializedName
import java.io.Serializable

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

class Movie: Serializable {

    @SerializedName("backdrop_path")
    var backdropPath: String? = null

    @SerializedName("original_language")
    var originalLanguage: String? = null

    @SerializedName("original_title")
    var originalTitle: String? = null

    @SerializedName("tagline")
    var tagline: String? = null

    @SerializedName("overview")
    var overview: String? = null
}













