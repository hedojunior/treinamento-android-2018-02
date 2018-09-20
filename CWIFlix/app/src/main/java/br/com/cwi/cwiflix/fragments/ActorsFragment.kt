package br.com.cwi.cwiflix.fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import br.com.cwi.cwiflix.R
import br.com.cwi.cwiflix.activities.PersonActivity
import br.com.cwi.cwiflix.adapters.PersonAdapter
import br.com.cwi.cwiflix.api.models.ListPerson
import br.com.cwi.cwiflix.api.models.Person
import br.com.cwi.cwiflix.listeners.EndlessRecyclerViewScrollListener
import br.com.cwi.cwiflix.presenters.ActorsPresenter
import br.com.cwi.cwiflix.views.ActorsView

import kotlinx.android.synthetic.main.fragment_actors.*
import kotlinx.android.synthetic.main.fragment_actors.view.*


class ActorsFragment : Fragment(), ActorsView {

    companion object {
        private const val TAG = "CWIFlix.ActorsFragment"
    }

    private lateinit var adapter: PersonAdapter

    private val presenter: ActorsPresenter by lazy {
        ActorsPresenter(this, 1)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_actors, container, false)
        val manager = view.recyclerView.layoutManager as LinearLayoutManager

        view.recyclerView.addOnScrollListener(object : EndlessRecyclerViewScrollListener(manager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                presenter.loadNextPage()
            }
        })

        presenter.getActorsList()

        return view
    }

    override fun onActorsRetrieved(actors: ArrayList<ListPerson>, isFirstFetch: Boolean) {
        if (isFirstFetch) {
            adapter = PersonAdapter(actors) {
                presenter.getActorDetail(it.id)
            }

            recyclerView.adapter = adapter

        } else {
            adapter.addPeople(actors)
        }
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

    override fun onLastPageReached() {
        recyclerView.clearOnScrollListeners()
        Toast.makeText(context, "Não há mais séries para exibir.", Toast.LENGTH_LONG).show()
    }
}
