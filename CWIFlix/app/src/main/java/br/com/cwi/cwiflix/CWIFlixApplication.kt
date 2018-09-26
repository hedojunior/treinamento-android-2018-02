package br.com.cwi.cwiflix

import android.app.Application
import android.content.Context
import br.com.cwi.cwiflix.services.SharedPreferencesService
import br.com.cwi.cwiflix.services.api.MovieDatabaseService
import br.com.cwi.cwiflix.utils.UserHolder
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

/**
 * @author hedo
 */
class CWIFlixApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val sharedPreferences =
                this.getSharedPreferences("CWIFlixApplication", Context.MODE_PRIVATE)

        SharedPreferencesService.sharedPreferences = sharedPreferences

        FirebaseApp.initializeApp(this)

        UserHolder.user = FirebaseAuth.getInstance().currentUser
    }
}






















