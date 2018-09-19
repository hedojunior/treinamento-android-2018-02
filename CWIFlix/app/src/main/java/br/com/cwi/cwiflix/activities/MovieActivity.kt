package br.com.cwi.cwiflix.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.cwi.cwiflix.R
import br.com.cwi.cwiflix.api.models.Movie
import br.com.cwi.cwiflix.utils.ImageURLProvider
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie.*


class MovieActivity : AppCompatActivity() {

    private var movie: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        movie = intent.getSerializableExtra("movie") as Movie

        movie?.let {
            Picasso.with(this).load(ImageURLProvider.large(it.backdropPath!!)).into(posterImageView)
            originalTitleTextView.text = it.originalTitle
            overviewTitleTextView.text = it.overview
            taglineTitleTextView.text = it.tagline
            originalLanguageTitleTextView.text = it.originalLanguage
        }

    }
}
