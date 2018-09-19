package br.com.cwi.cwiflix.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.cwi.cwiflix.R
import br.com.cwi.cwiflix.api.models.Series
import br.com.cwi.cwiflix.utils.ImageURLProvider
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie.*


class SeriesActivity : AppCompatActivity() {

    private var series: Series? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_series)

        series = intent.getSerializableExtra("series") as Series

        series?.let {
            Picasso.with(this).load(ImageURLProvider.large(it.backdropPath!!)).into(posterImageView)
            originalTitleTextView.text = it.name
            overviewTitleTextView.text = it.overview
            taglineTitleTextView.text = it.tagline
            originalLanguageTitleTextView.text = it.originalLanguage
        }

    }
}
