package br.com.cwi.cwiflix.presenters

import android.app.Activity
import br.com.cwi.cwiflix.R
import br.com.cwi.cwiflix.views.LoginView
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

/**
 * @author hedo
 */
class LoginPresenter(private val view: LoginView) {

    companion object {
        const val REQUEST_CODE = 9000
    }

    fun logIn(activity: Activity) {
        val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestProfile()
                .requestIdToken(activity.getString(R.string.default_web_client_id))
                .build()

        val client = GoogleSignIn.getClient(activity, options)
        activity.startActivityForResult(client.signInIntent, REQUEST_CODE)
    }


}


















