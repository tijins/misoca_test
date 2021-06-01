package jp.misoca.sampleintentstest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import jp.misoca.sampleintentstest.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit)
        binding.editText.setText(intent.getStringExtra(Intent.EXTRA_TEXT))
        binding.btnSave.setOnClickListener {
            save()
        }
    }

    private fun save() {
        val data = Intent().apply {
            putExtra(Intent.EXTRA_TEXT, binding.editText.text.toString())
        }
        setResult(RESULT_OK, data)
        finish()
    }
}
