package br.com.cwi.cwiflix.presenters

import br.com.cwi.cwiflix.api.MovieDatabaseService
import br.com.cwi.cwiflix.api.models.Person
import br.com.cwi.cwiflix.api.models.PersonResult
import br.com.cwi.cwiflix.views.ActorsView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @author hedo
 */
class ActorsPresenter(private val view: ActorsView) : Callback<PersonResult> {

    fun getActorsList() {
        MovieDatabaseService.service.getPopularPeople().enqueue(this)
    }

    override fun onResponse(call: Call<PersonResult>, response: Response<PersonResult>) {
        response.body()?.results?.let {
            view.onActorsRetrieved(it)
        }
    }

    override fun onFailure(call: Call<PersonResult>, t: Throwable) {
        view.onActorsRetrieveFailure(t)
    }

    fun getActorDetail(id: Int) {
        MovieDatabaseService.service.getPersonDetail(id).enqueue(object : Callback<Person> {
            override fun onResponse(call: Call<Person>?, response: Response<Person>?) {
                response?.body()?.let {
                    view.onDetailResponse(it)
                }
            }

            override fun onFailure(call: Call<Person>?, t: Throwable?) {
                t?.run { view.onDetailFailure(this) }
            }
        })
    }
}