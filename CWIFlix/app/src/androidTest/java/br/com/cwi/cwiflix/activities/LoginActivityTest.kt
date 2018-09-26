package br.com.cwi.cwiflix.activities

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import br.com.cwi.cwiflix.R
import br.com.cwi.cwiflix.adapters.PosterViewHolder
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @author hedo
 */
@LargeTest
@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    @get:Rule
    val rule = ActivityTestRule<LoginActivity>(LoginActivity::class.java)

    @Test
    fun loginActivityTest() {
        onView(withId(R.id.emailEditText))
                .perform(
                        replaceText("testeabc123@gmail.com"),
                        closeSoftKeyboard())

        onView(withId(R.id.passwordEditText))
                .perform(replaceText("123456"),
                        closeSoftKeyboard())

        onView(withId(R.id.logInButton)).perform(click())
        
        Thread.sleep(5000)

        onView(withId(R.id.logout)).perform(click())
    }


}