package br.com.cwi.cwiflix.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.cwi.cwiflix.R
import br.com.cwi.cwiflix.services.api.models.Series
import br.com.cwi.cwiflix.utils.ImageURLProvider
import br.com.cwi.cwiflix.utils.loadImage
import kotlinx.android.synthetic.main.activity_series.*

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
        }
    }
}
