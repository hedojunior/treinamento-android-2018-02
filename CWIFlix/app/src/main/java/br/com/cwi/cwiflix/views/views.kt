package br.com.cwi.cwiflix.views

import br.com.cwi.cwiflix.api.models.ListPerson
import br.com.cwi.cwiflix.api.models.Media
import br.com.cwi.cwiflix.api.models.Person

/**
 * @author hedo
 */

interface MediaView<T : Media> {
    fun onResponse(list: ArrayList<Media>, isFirstFetch: Boolean)
    fun onFailure(throwable: Throwable)
    fun onDetailResponse(media: T)
    fun onDetailFailure(throwable: Throwable)
    fun onLastPageReached()
}

interface ActorsView {
    fun onActorsRetrieved(actors: ArrayList<ListPerson>, isFirstFetch: Boolean)
    fun onActorsRetrieveFailure(throwable: Throwable)
    fun onDetailResponse(person: Person)
    fun onDetailFailure(throwable: Throwable)
    fun onLastPageReached()
}

interface LoginView {
    fun onLoginSucceeded()
    fun onLoginFailed()
}





















