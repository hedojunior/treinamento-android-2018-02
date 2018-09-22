package br.com.cwi.cwiflix.fragments.dialogs

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import br.com.cwi.cwiflix.R
import br.com.cwi.cwiflix.activities.MainActivity
import br.com.cwi.cwiflix.presenters.SignUpPresenter
import br.com.cwi.cwiflix.utils.UserHolder
import br.com.cwi.cwiflix.views.SignUpView
import kotlinx.android.synthetic.main.dialog_sign_up.*

/**
 * @author hedo
 */
class SignUpDialogFragment : DialogFragment(), SignUpView {
    private val presenter: SignUpPresenter by lazy {
        SignUpPresenter(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signUpButton.setOnClickListener {
            presenter.signUp(
                    emailEditText.text.toString(),
                    passwordEditText.text.toString()
            )
        }
    }

    override fun onSignUpSucceeded() {
        Toast.makeText(context, "Olá, ${UserHolder.user?.email}", Toast.LENGTH_SHORT).show()
        val intent = Intent(context, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onSignUpFailed(reason: String?) {
        Toast.makeText(context, reason, Toast.LENGTH_LONG).show()
    }
}