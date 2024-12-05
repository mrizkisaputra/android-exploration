package com.mrizkisaputra

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract

class MyCustomContract : ActivityResultContract<String, String?>() {
    override fun createIntent(context: Context, input: String): Intent {
        val intent = Intent(context, SecondActivity::class.java)
        intent.putExtra(SecondActivity.EXTRA_DATA, input)
        return intent
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? {
        if (resultCode == 200 && intent != null) {
            val data = intent.getStringExtra(SecondActivity.EXTRA_DATA)
        }
        return null
    }
}