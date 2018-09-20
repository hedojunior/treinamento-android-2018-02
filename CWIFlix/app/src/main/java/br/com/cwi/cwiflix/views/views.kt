package br.com.cwi.cwiflix.views

import br.com.cwi.cwiflix.api.models.ListPerson
import br.com.cwi.cwiflix.api.models.Media
import br.com.cwi.cwiflix.api.models.Person

/**
 * @author hedo
 */

interface MediaView<T : Media> {
    fun onResponse(list: List<Media>)
    fun onFailure(throwable: Throwable)
    fun onDetailResponse(media: T)
    fun onDetailFailure(throwable: Throwable)
}

interface ActorsView {
    fun onActorsRetrieved(actors: List<ListPerson>)
    fun onActorsRetrieveFailure(throwable: Throwable)
    fun onDetailResponse(person: Person)
    fun onDetailFailure(throwable: Throwable)
}