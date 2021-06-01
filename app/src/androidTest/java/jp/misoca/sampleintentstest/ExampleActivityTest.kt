package jp.misoca.sampleintentstest

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleActivityTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testEdit() {
        Espresso.onView(ViewMatchers.withId(R.id.txt_text)).perform(
            ViewActions.typeText("Hello World")
        )
        Espresso.onView(ViewMatchers.withText("編集")).perform(
            ViewActions.click()
        )
        Espresso.onView(ViewMatchers.withId(R.id.txt_edit)).check(
            ViewAssertions.matches(
                ViewMatchers.withText("Hello World")
            )
        )
    }
}
