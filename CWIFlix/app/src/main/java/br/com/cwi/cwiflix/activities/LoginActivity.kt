package br.com.cwi.cwiflix.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.cwi.cwiflix.R
import br.com.cwi.cwiflix.presenters.LoginPresenter
import br.com.cwi.cwiflix.utils.UserHolder
import br.com.cwi.cwiflix.views.LoginView
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginView {

    private val presenter: LoginPresenter by lazy {
        LoginPresenter(this)
    }

    override fun onLoginSucceeded(account: FirebaseUser?) {
        Toast.makeText(this, "Olá, ${account?.displayName}", Toast.LENGTH_SHORT).show()
        goToMainActivity()
    }

    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)

        startActivity(intent)
    }

    override fun onLoginFailed() {
        Toast.makeText(this,
                getString(R.string.try_again_login),
                Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        googleSignInButton.setOnClickListener {
            presenter.logIn(this)
        }

        if (UserHolder.isLoggedIn()) {
            goToMainActivity()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == LoginPresenter.REQUEST_CODE) {
            presenter.handleLoginResult(data)
        }
    }
}
















