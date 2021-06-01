package jp.misoca.sampleintentstest

import android.content.ComponentName
import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.intent.Intents
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
        Espresso.onView(ViewMatchers.withId(R.id.txt_text)).perform(
            ViewActions.typeText("Hello World")
        )
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
        assertThat(intent).extras().string(Intent.EXTRA_TEXT).isEqualTo("Hello World")
    }
    
    @Test
    fun testShare() {
        Espresso.onView(ViewMatchers.withId(R.id.txt_text)).perform(
            ViewActions.typeText("Hello World")
        )
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
        assertThat(intent).extras().string(Intent.EXTRA_TEXT).isEqualTo("Hello World")
    }
}
