package br.com.cwi.cwiflix.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.cwi.cwiflix.R
import br.com.cwi.cwiflix.api.models.Movie
import br.com.cwi.cwiflix.utils.ImageURLProvider
import br.com.cwi.cwiflix.utils.loadImage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie.*


class MovieActivity : AppCompatActivity() {
    private lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        movie = intent.getSerializableExtra("movie") as Movie

        movie.run {
            posterImageView.loadImage(ImageURLProvider.large(image))
            originalTitleTextView.text = title
            overviewTitleTextView.text = overview
            taglineTitleTextView.text = tagline
            originalLanguageTitleTextView.text = originalLanguage

            this@MovieActivity.title = title
        }
    }
}
