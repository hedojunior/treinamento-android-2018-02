//package br.com.cwi.cwiflix.activities;
//
//
//import android.support.test.espresso.ViewInteraction;
//import android.support.test.filters.LargeTest;
//import android.support.test.rule.ActivityTestRule;
//import android.support.test.runner.AndroidJUnit4;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.ViewParent;
//
//import org.hamcrest.Description;
//import org.hamcrest.Matcher;
//import org.hamcrest.TypeSafeMatcher;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import br.com.cwi.cwiflix.R;
//
//import static android.support.test.espresso.Espresso.onView;
//import static android.support.test.espresso.Espresso.pressBack;
//import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
//import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
//import static android.support.test.espresso.action.ViewActions.replaceText;
//import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
//import static android.support.test.espresso.matcher.ViewMatchers.withId;
//import static android.support.test.espresso.matcher.ViewMatchers.withText;
//import static org.hamcrest.Matchers.allOf;
//
//@LargeTest
//@RunWith(AndroidJUnit4.class)
//public class LoginActivityTest {
//
//    @Rule
//    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);
//
//    @Test
//    public void loginActivityTest() {
//        // Added a sleep statement to match the app's execution delay.
//        // The recommended way to handle such scenarios is to use Espresso idling resources:
//        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
//        try {
//            Thread.sleep(60000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        ViewInteraction textInputEditText = onView(
//                allOf(withId(R.id.emailEditText),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(R.id.emailInputLayout),
//                                        0),
//                                0),
//                        isDisplayed()));
//        textInputEditText.perform(replaceText("teste"), closeSoftKeyboard());
//
//        // Added a sleep statement to match the app's execution delay.
//        // The recommended way to handle such scenarios is to use Espresso idling resources:
//        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
//        try {
//            Thread.sleep(60000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        ViewInteraction textInputEditText2 = onView(
//                allOf(withId(R.id.emailEditText), withText("teste"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(R.id.emailInputLayout),
//                                        0),
//                                0),
//                        isDisplayed()));
//        textInputEditText2.perform(replaceText("testeabc123@gmail.com"));
//
//        ViewInteraction textInputEditText3 = onView(
//                allOf(withId(R.id.emailEditText), withText("testeabc123@gmail.com"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(R.id.emailInputLayout),
//                                        0),
//                                0),
//                        isDisplayed()));
//        textInputEditText3.perform(closeSoftKeyboard());
//
//        ViewInteraction textInputEditText4 = onView(
//                allOf(withId(R.id.passwordEditText),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(R.id.passwordInputLayout),
//                                        0),
//                                0),
//                        isDisplayed()));
//        textInputEditText4.perform(replaceText("123456"), closeSoftKeyboard());
//
//        ViewInteraction textInputEditText5 = onView(
//                allOf(withId(R.id.passwordEditText), withText("123456"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(R.id.passwordInputLayout),
//                                        0),
//                                0),
//                        isDisplayed()));
//        textInputEditText5.perform(pressImeActionButton());
//
//        pressBack();
//
//    }
//
//    private static Matcher<View> childAtPosition(
//            final Matcher<View> parentMatcher, final int position) {
//
//        return new TypeSafeMatcher<View>() {
//            @Override
//            public void describeTo(Description description) {
//                description.appendText("Child at position " + position + " in parent ");
//                parentMatcher.describeTo(description);
//            }
//
//            @Override
//            public boolean matchesSafely(View view) {
//                ViewParent parent = view.getParent();
//                return parent instanceof ViewGroup && parentMatcher.matches(parent)
//                        && view.equals(((ViewGroup) parent).getChildAt(position));
//            }
//        };
//    }
//}
