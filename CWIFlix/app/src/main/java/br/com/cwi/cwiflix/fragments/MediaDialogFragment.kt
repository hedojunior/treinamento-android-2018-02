package br.com.cwi.cwiflix.fragments

import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.cwi.cwiflix.R
import br.com.cwi.cwiflix.SharedPreferencesService
import br.com.cwi.cwiflix.api.models.Media
import kotlinx.android.synthetic.main.dialog_media.*

import kotlinx.android.synthetic.main.dialog_media.view.*

/**
 * @author hedo
 */
class MediaDialogFragment : DialogFragment() {
    lateinit var media: Media

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dialog_media, container, false)

        SharedPreferencesService.retrieveInt(media.id.toString()).run {
            if (this != -1) {
                view.mediaRatingBar.rating = this.toFloat() / 2
                view.myRatingTextView.text = "$this/10"
            }
        }

        media.run {
            view.titleTextView.text = title
            view.overviewTextView.text = overview
            view.releaseDateTextView.text = releaseDate
            view.ratingTextView.text = "$voteAverage/10"
        }

        view.mediaRatingBar.setOnRatingBarChangeListener { _, rating, _ ->
            val castRating = (rating * 2).toInt()
            myRatingTextView.text = "$castRating/10"
            SharedPreferencesService.write(media.id.toString(), castRating)
        }

        return view
    }
}









