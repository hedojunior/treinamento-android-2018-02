package br.com.cwi.cwiflix.views

import br.com.cwi.cwiflix.api.models.ListPerson
import br.com.cwi.cwiflix.api.models.Media
import br.com.cwi.cwiflix.api.models.Person
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseUser

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
    fun onNormalLoginFailed(reason: String?)
}

interface SignUpView {
    fun onSignUpSucceeded()
    fun onSignUpFailed(reason: String?)
}





















