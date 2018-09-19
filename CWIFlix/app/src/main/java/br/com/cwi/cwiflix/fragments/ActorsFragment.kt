package br.com.cwi.cwiflix.fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.cwi.cwiflix.R
import br.com.cwi.cwiflix.activities.MovieActivity
import br.com.cwi.cwiflix.activities.PersonActivity
import br.com.cwi.cwiflix.adapters.PersonAdapter
import br.com.cwi.cwiflix.api.MovieDatabaseService
import br.com.cwi.cwiflix.api.models.Movie
import br.com.cwi.cwiflix.api.models.Person
import br.com.cwi.cwiflix.api.models.PersonResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import kotlinx.android.synthetic.main.fragment_actors.*


class ActorsFragment : Fragment(), Callback<PersonResult> {
    lateinit var adapter: PersonAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        MovieDatabaseService.service.getPopularPeople().enqueue(this)
        return inflater.inflate(R.layout.fragment_actors, container, false)
    }

    override fun onFailure(call: Call<PersonResult>, t: Throwable) {
        Log.e(
                "ActorsFragment",
                "An error occurred while fetching the actors: "
                        + t.localizedMessage,
                t
        )
    }

    override fun onResponse(call: Call<PersonResult>, response: Response<PersonResult>) {
        response.body()?.results?.let { it ->
            adapter = PersonAdapter(it) {
                getPersonDetail(it.id!!)
            }
            recyclerView.adapter = adapter
        }
    }

    private fun getPersonDetail(id: Int) {
        val request = MovieDatabaseService.service.getPersonDetail(id)

        request.enqueue(object : Callback<Person> {

            override fun onResponse(call: Call<Person>?, response: Response<Person>?) {

                val intent = Intent(activity, PersonActivity::class.java)
                intent.putExtra("person", response?.body())

                activity?.startActivity(intent)
            }

            override fun onFailure(call: Call<Person>?, t: Throwable?) {
            }
        })
    }


}
