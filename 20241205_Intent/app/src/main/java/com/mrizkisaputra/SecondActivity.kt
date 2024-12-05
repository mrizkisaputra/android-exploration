package com.mrizkisaputra

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.mrizkisaputra.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = intent.getParcelableArrayListExtra<User>(EXTRA_USER)
        if (user != null) {
            user.forEach(fun(u: User) {
                Log.i(TAG, u.toString())
            })
        }

        binding.buttonSendResult.setOnClickListener {
            val data = binding.edtInput.text.toString()
            val intent = Intent().apply {
                putExtra(EXTRA_DATA, data)
            }
            setResult(200, intent)
            finish()
        }

    }

    companion object {
        private val TAG = SecondActivity::class.simpleName
        val EXTRA_USER = "EXTRA_USER"
        val EXTRA_DATA = "EXTRA_DATA"
    }
}