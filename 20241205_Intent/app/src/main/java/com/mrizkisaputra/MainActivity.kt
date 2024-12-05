package com.mrizkisaputra

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.registerForActivityResult
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mrizkisaputra.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var dummyData: ArrayList<User> = arrayListOf(
        User("user1", "user1@gmail.com", "rahasia"),
        User("user2", "user2@gmail.com", "rahasia"),
        User("user3", "user3@gmail.com", "rahasia"),
    )

    // contoh menggunakan contract result activity bawaan
    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == 200 && result.data != null) {
                val data = result.data?.getStringExtra(SecondActivity.EXTRA_DATA)
                Toast.makeText(this, data, Toast.LENGTH_SHORT).show()
            }
        }

    // menggunakan contract result activity kostum
    private val resultLauncherCustom =
        registerForActivityResult(MyCustomContract()) { result ->
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
        }

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
        // explicit intent
        binding.buttonGoToSecondActivity.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        binding.buttonGotToSecondActivityWithData.setOnClickListener {
            Intent(this, SecondActivity::class.java).apply {
                putParcelableArrayListExtra(SecondActivity.EXTRA_USER, dummyData)
                startActivity(this)
            }
        }

        // activity result (legacy)
//        binding.buttonActivityResult.setOnClickListener {
//            val intent = Intent(this, SecondActivity::class.java)
//            startActivityForResult(intent, REQUEST_CODE)
//        }

        binding.buttonActivityResult.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            resultLauncher.launch(intent)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == 200) {
            val result = data?.getStringExtra(SecondActivity.EXTRA_DATA)
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
        }
    }


    companion object {
        val REQUEST_CODE = 100
    }

}