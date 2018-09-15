package br.com.cwi.cwiflix.api.models

import com.google.gson.annotations.SerializedName

/**
 * @author hedo
 */

class MediaResult(val results: List<Media>)

class Media(
        @SerializedName("title",
                alternate = ["name"]) val title: String,
        val overview: String,
        @SerializedName("poster_path") val image: String
)