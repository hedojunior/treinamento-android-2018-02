package br.com.cwi.cwiflix.presenters

import br.com.cwi.cwiflix.utils.UserHolder
import br.com.cwi.cwiflix.views.SignUpView
import com.google.firebase.auth.FirebaseAuth

/**
 * @author hedo
 */
class SignUpPresenter(private val view: SignUpView) {
    private val client: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    fun signUp(email: String, password: String) {
        if (!email.isBlank() && !password.isBlank()) {
            client.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            UserHolder.user = client.currentUser
                            view.onSignUpSucceeded()
                        } else {
                            view.onSignUpFailed(it.exception?.localizedMessage)
                        }
                    }
        } else {
            view.onSignUpFailed("Dados inválidos, verifique-os e então tente novamente.")
        }
    }
}