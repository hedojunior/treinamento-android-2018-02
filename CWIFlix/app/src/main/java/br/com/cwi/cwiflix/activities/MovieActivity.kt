package br.com.cwi.cwiflix.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.cwi.cwiflix.R
import br.com.cwi.cwiflix.services.FavoritesService
import br.com.cwi.cwiflix.services.api.models.Media
import br.com.cwi.cwiflix.services.api.models.MediaType
import br.com.cwi.cwiflix.services.api.models.Movie
import br.com.cwi.cwiflix.services.models.Favorite
import br.com.cwi.cwiflix.utils.ImageURLProvider
import br.com.cwi.cwiflix.utils.loadImage
import kotlinx.android.synthetic.main.activity_movie.*
import kotlinx.android.synthetic.main.fab_star.*

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

            changeButtonState(FavoritesService.isFavorite(id!!, MediaType.MOVIE))

            starFloatingButton.setOnClickListener {
                val isFavorite = FavoritesService.isFavorite(id, MediaType.MOVIE)

                if (isFavorite) {
                    FavoritesService.remove(id, MediaType.MOVIE)
                } else {
                    val favorite = Favorite(id, image, MediaType.MOVIE)
                    FavoritesService.add(favorite)
                }

                changeButtonState(!isFavorite)
            }
        }
    }

    private fun changeButtonState(isFavorite: Boolean) {
        val resource = if (isFavorite) R.drawable.ic_star else R.drawable.ic_star_border
        starFloatingButton.setImageResource(resource)
    }
}









