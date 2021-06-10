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
        // メイン画面で実行される
        Espresso.onView(ViewMatchers.withId(R.id.btn_edit)).perform(
            ViewActions.click()
        )

        // 編集画面で実行される
        // 初期表示の確認
        Espresso.onView(ViewMatchers.withId(R.id.edit_text)).check(
            ViewAssertions.matches(
                ViewMatchers.withText("Hello")
            )
        )
        // EditTextに入力する
        Espresso.onView(ViewMatchers.withId(R.id.edit_text)).perform(
            ViewActions.replaceText("Bye!")
        )
        // 保存ボタンをクリックする
        Espresso.onView(ViewMatchers.withId(R.id.btn_save)).perform(
            ViewActions.click()
        )

        // メイン画面で実行される
        // 編集結果が反映されている
        Espresso.onView(ViewMatchers.withId(R.id.txt_text)).check(
            ViewAssertions.matches(
                ViewMatchers.withText("Bye!")
            )
        )
    }
}
