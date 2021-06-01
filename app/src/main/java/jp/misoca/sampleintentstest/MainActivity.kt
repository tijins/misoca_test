package jp.misoca.sampleintentstest

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import jp.misoca.sampleintentstest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val editLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) {
            onEdit(it.data!!)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.txtText.text = "Hello"
        binding.btnEdit.setOnClickListener {
            openEditActivity()
        }
        binding.btnShare.setOnClickListener {
            share()
        }
    }

    private fun openEditActivity() {
        val intent = Intent(this, EditActivity::class.java).apply {
            putExtra(Intent.EXTRA_TEXT, binding.txtText.text.toString())
        }
        editLauncher.launch(intent)
    }

    private fun onEdit(data: Intent) {
        binding.txtText.text = data.getStringExtra(Intent.EXTRA_TEXT)
    }

    private fun share() {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, binding.txtText.text.toString())
        }
        val chooser = Intent.createChooser(intent, getString(R.string.main_share))
        startActivity(chooser)
    }
}
