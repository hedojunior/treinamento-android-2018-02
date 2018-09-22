package br.com.cwi.cwiflix.services.models

import br.com.cwi.cwiflix.services.api.models.MediaType

/**
 * @author hedo
 */
class Favorite() {
    var id: String = ""

    var mediaId: Int = 0
        private set
    var image: String? = ""
        private set
    var mediaType: MediaType = MediaType.MOVIE
        private set

    constructor(mediaId: Int, image: String?, mediaType: MediaType) : this() {
        this.mediaId = mediaId
        this.image = image
        this.mediaType = mediaType
    }
}
