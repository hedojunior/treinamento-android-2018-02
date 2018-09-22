package br.com.cwi.cwiflix.presenters

import android.app.Activity
import android.content.Intent
import android.util.Log
import br.com.cwi.cwiflix.R
import br.com.cwi.cwiflix.utils.UserHolder
import br.com.cwi.cwiflix.views.LoginView
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

/**
 * @author hedo
 */
class LoginPresenter(private val view: LoginView) {

    companion object {
        const val REQUEST_CODE = 9000
        const val TAG = "CWIFlix.LoginPresenter"
    }

    private val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    fun logIn(activity: Activity) {
        val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestProfile()
                .requestIdToken(activity.getString(R.string.default_web_client_id))
                .build()

        UserHolder.signInOptions = options
        val client = GoogleSignIn.getClient(activity, options)
        activity.startActivityForResult(client.signInIntent, REQUEST_CODE)
    }

    fun logIn(email: String, password: String) {
        if (!email.isBlank() && !password.isBlank()) {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            UserHolder.user = firebaseAuth.currentUser
                            view.onLoginSucceeded()
                        } else {
                            view.onNormalLoginFailed(it.exception?.localizedMessage)
                        }
                    }
        } else {
            view.onLoginFailed()
        }
    }

    fun handleLoginResult(data: Intent?) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)

        try {
            val account = task.getResult(ApiException::class.java)
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)

            firebaseAuth.signInWithCredential(credential)
                    .addOnCompleteListener {
                        if (task.isSuccessful) {
                            UserHolder.user = firebaseAuth.currentUser
                            view.onLoginSucceeded()
                        } else {
                            Log.e(TAG, "LoginPresenter.handleLoginResult@signInWithCredential: " + task.exception?.localizedMessage, task.exception)
                            view.onLoginFailed()
                        }
                    }

        } catch (exception: ApiException) {
            Log.e(TAG, "LoginPresenter.handleLoginResult: " + exception.localizedMessage, exception)
            view.onLoginFailed()
        }
    }
}


















