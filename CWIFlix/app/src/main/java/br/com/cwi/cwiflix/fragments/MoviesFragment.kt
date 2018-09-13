package br.com.cwi.cwiflix.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.cwi.cwiflix.R

class MoviesFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("fragmentCycle - Movies", "onCreate")
    }

    override fun onResume() {
        super.onResume()

        Log.d("fragmentCycle - Movies", "onResume")
    }

    override fun onPause() {
        super.onPause()

        Log.d("fragmentCycle - Movies", "onPause")
    }

    override fun onStop() {
        super.onStop()

        Log.d("fragmentCycle - Movies", "onStop")
    }
}