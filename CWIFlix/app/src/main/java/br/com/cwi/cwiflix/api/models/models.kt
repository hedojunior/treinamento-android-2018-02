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


class Movie(
        val overview: String?,
        @SerializedName("backdrop_path") val backdropPath: String?,
        @SerializedName("original_language") val originalLanguage: String?,
        @SerializedName("original_title") val originalTitle: String?,
        @SerializedName("tagline") val tagline: String?
): Serializable

class Series(
        val overview: String?,
        @SerializedName("backdrop_path") val backdropPath: String?,
        @SerializedName("original_language") val originalLanguage: String?,
        @SerializedName("name") val name: String?,
        @SerializedName("tagline") val tagline: String?
): Serializable

class Person(
        @SerializedName("profile_path") val image: String?,
        val name: String?,
        val biography: String?,
        @SerializedName("place_of_birth") val placeOfBirth: String?,
        val birthday: String?
): Serializable












