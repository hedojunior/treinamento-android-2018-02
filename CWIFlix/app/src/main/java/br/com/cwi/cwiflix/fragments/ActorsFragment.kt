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
import br.com.cwi.cwiflix.api.models.ListPerson
import br.com.cwi.cwiflix.api.models.Movie
import br.com.cwi.cwiflix.api.models.Person
import br.com.cwi.cwiflix.api.models.PersonResult
import br.com.cwi.cwiflix.presenters.ActorsPresenter
import br.com.cwi.cwiflix.views.ActorsView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import kotlinx.android.synthetic.main.fragment_actors.*


class ActorsFragment : Fragment(), ActorsView {

    companion object {
        private const val TAG = "CWIFlix.ActorsFragment"
    }

    private lateinit var adapter: PersonAdapter

    private val presenter: ActorsPresenter by lazy {
        ActorsPresenter(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        presenter.getActorsList()
        return inflater.inflate(R.layout.fragment_actors, container, false)
    }

    override fun onActorsRetrieved(actors: List<ListPerson>) {
        adapter = PersonAdapter(actors) {
            presenter.getActorDetail(it.id)
        }

        recyclerView.adapter = adapter
    }

    override fun onActorsRetrieveFailure(throwable: Throwable) {
        throwable.run {
            Log.e(TAG, "ActorsFragment.onActorsRetrieveFailure: $localizedMessage", this)
        }
    }

    override fun onDetailResponse(person: Person) {
        val intent = Intent(activity, PersonActivity::class.java)
        intent.putExtra("person", person)

        activity?.startActivity(intent)
    }

    override fun onDetailFailure(throwable: Throwable) {
        throwable.run {
            Log.e(TAG, "ActorsFragment.onDetailFailure: $localizedMessage", this)
        }
    }
}
