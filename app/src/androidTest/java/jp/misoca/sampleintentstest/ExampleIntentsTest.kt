package jp.misoca.sampleintentstest

import android.app.Activity
import android.app.Instrumentation
import android.content.ComponentName
import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.ext.truth.content.IntentSubject.assertThat
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExampleIntentsTest {

    @get:Rule
    val activityRule = IntentsTestRule(MainActivity::class.java)

    private val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    @Test
    fun testEdit() {
        Espresso.onView(ViewMatchers.withText("編集")).perform(
            ViewActions.click()
        )

        val intents = Intents.getIntents()
        val intent = intents.first()
        assertThat(intent).hasComponent(
            ComponentName(
                "jp.misoca.sampleintentstest",
                "jp.misoca.sampleintentstest.EditActivity"
            )
        )
        assertThat(intent).extras().string(Intent.EXTRA_TEXT).isEqualTo("Hello")
    }

    @Test
    fun testOnEdit() {
        // intendingを使用して、編集画面をスタブ化する
        val resultData = Intent().apply {
            putExtra(Intent.EXTRA_TEXT, "Bye!")
        }
        val result = Instrumentation.ActivityResult(Activity.RESULT_OK, resultData)

        intending(
            IntentMatchers.hasComponent(
                ComponentName(
                    "jp.misoca.sampleintentstest",
                    "jp.misoca.sampleintentstest.EditActivity"
                )
            )
        ).respondWith(result)

        // intendingされているので、EditActivityの起動後、するにonActivityResultに処理が戻る
        Espresso.onView(ViewMatchers.withText("編集")).perform(
            ViewActions.click()
        )

        //結果をチェック
        Espresso.onView(ViewMatchers.withId(R.id.txt_text)).check(
            ViewAssertions.matches(
                ViewMatchers.withText("Bye!")
            )
        )
    }

    @Test
    fun testShare() {
        Espresso.onView(ViewMatchers.withText("共有")).perform(
            ViewActions.click()
        )
        // 戻るボタンを押さないと次のテストが開始されない
        device.pressBack()

        // IntentChooserが使用されている
        val chooser = Intents.getIntents().first()
        assertThat(chooser).hasAction(Intent.ACTION_CHOOSER)

        val intent = chooser.getParcelableExtra<Intent>(Intent.EXTRA_INTENT)
        assertThat(intent).hasAction(Intent.ACTION_SEND)
        assertThat(intent).hasType("text/plain")
        assertThat(intent).extras().string(Intent.EXTRA_TEXT).isEqualTo("Hello")
    }
}
