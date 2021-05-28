package jp.misoca.sampleintentstest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import jp.misoca.sampleintentstest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.btnEdit.setOnClickListener {
            openEditActivity()
        }
        binding.btnShare.setOnClickListener {
            share()
        }
    }

    private fun openEditActivity(){
        val intent = Intent(this, EditActivity::class.java).apply {
            putExtra(Intent.EXTRA_TEXT, binding.txtSubject.text.toString())
        }
        startActivity(intent)
    }

    private fun share(){
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, binding.txtSubject.text.toString())
        }
        val chooser = Intent.createChooser(intent,getString(R.string.main_share))
        startActivity(chooser)
    }
}
