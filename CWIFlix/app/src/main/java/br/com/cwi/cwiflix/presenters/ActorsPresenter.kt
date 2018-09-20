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
class ActorsPresenter(private val view: ActorsView, private var page: Int) : Callback<PersonResult> {

    private var lastPage: Int = 0

    fun getActorsList() {
        MovieDatabaseService.service.getPopularPeople(page).enqueue(this)
    }

    fun loadNextPage() {
        page++
        if (page <= lastPage) {
            getActorsList()
        } else {
            view.onLastPageReached()
        }
    }

    override fun onResponse(call: Call<PersonResult>, response: Response<PersonResult>) {
        response.body()?.run {
            lastPage = totalPages
            view.onActorsRetrieved(results, page == 1)
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