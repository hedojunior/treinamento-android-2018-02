package br.com.cwi.cwiflix.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.cwi.cwiflix.R
import br.com.cwi.cwiflix.api.models.Person
import br.com.cwi.cwiflix.utils.ImageURLProvider
import br.com.cwi.cwiflix.utils.loadImage
import kotlinx.android.synthetic.main.activity_person.*

class PersonActivity : AppCompatActivity() {

    private lateinit var person: Person

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person)

        person = intent.getSerializableExtra("person") as Person

        person.run {
            personImageView.loadImage(ImageURLProvider.large(image))
            nameTextView.text = name
            placeOfBirthTextView.text = placeOfBirth
            biographyTextView.text = biography
            birthDayTextView.text = birthday

            this@PersonActivity.title = name
        }
    }
}
