package com.mrizkisaputra

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mrizkisaputra.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // -----------------------------------------------------------------------------------------
        // implicit intent
        binding.buttonActionDial.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:082174456473"))
            try {
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {

            }
        }

        binding.buttonActionMap.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=1600+Amphitheatre+Parkway,+Mountain+View,+California"))
            try {
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {

            }
        }

        binding.buttonActionWeb.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://mrizkisaputra.github.io/"))
            try {
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {

            }
        }

        binding.buttonActionEmail.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND).apply {
                this.type = "text/plain"
                this.putExtra(Intent.EXTRA_EMAIL, arrayOf("mrizkisaputra6@gmail.com", "mrizkisaputra2511@gmail.com"))
                this.putExtra(Intent.EXTRA_SUBJECT, "Email Subject")
                this.putExtra(Intent.EXTRA_TEXT, "this is message")
            }

            val chooser = Intent.createChooser(intent, "pilih !!z`")
            try {
                startActivity(chooser)
            } catch (e: ActivityNotFoundException) {

            }
        }


    }


}