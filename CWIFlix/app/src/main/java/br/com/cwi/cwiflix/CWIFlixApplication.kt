package br.com.cwi.cwiflix

import android.app.Application
import android.content.Context
import br.com.cwi.cwiflix.utils.SharedPreferencesService

/**
 * @author hedo
 */
class CWIFlixApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val sharedPreferences =
                this.getSharedPreferences("CWIFlixApplication", Context.MODE_PRIVATE)

        SharedPreferencesService.sharedPreferences = sharedPreferences
    }
}