package jp.misoca.sampleintentstest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import jp.misoca.sampleintentstest.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {

    private lateinit var binding:ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit)


        binding.editText.setText(intent.getStringExtra(Intent.EXTRA_TEXT))
    }
}
