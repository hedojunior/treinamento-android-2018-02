package br.com.cwi.cwiflix.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.cwi.cwiflix.R
import br.com.cwi.cwiflix.services.FavoritesService
import br.com.cwi.cwiflix.services.api.models.MediaType
import br.com.cwi.cwiflix.services.api.models.Series
import br.com.cwi.cwiflix.services.models.Favorite
import br.com.cwi.cwiflix.utils.ImageURLProvider
import br.com.cwi.cwiflix.utils.loadImage
import kotlinx.android.synthetic.main.activity_series.*
import kotlinx.android.synthetic.main.fab_star.*

class SeriesActivity : AppCompatActivity() {
    private lateinit var series: Series

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_series)

        series = intent.getSerializableExtra("series") as Series

        series.run {
            posterImageView.loadImage(ImageURLProvider.large(image))
            originalTitleTextView.text = title
            overviewTitleTextView.text = overview
            taglineTitleTextView.text = tagline
            originalLanguageTitleTextView.text = originalLanguage

            this@SeriesActivity.title = title

            changeButtonState(FavoritesService.isFavorite(id!!, MediaType.TV_SHOW))

            starFloatingButton.setOnClickListener {
                val isFavorite = FavoritesService.isFavorite(id, MediaType.TV_SHOW)

                if (isFavorite) {
                    FavoritesService.remove(id, MediaType.TV_SHOW)
                } else {
                    val favorite = Favorite(id, image, MediaType.TV_SHOW)
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
