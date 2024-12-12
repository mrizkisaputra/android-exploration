package com.mrizkisaputra

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mrizkisaputra.databinding.ActivityContactBinding

class ContactActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityContactBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchBar.inflateMenu(R.menu.activity_main)

            searchView
                .editText
                .setOnEditorActionListener { v, actionId, event ->
                    searchBar.setText(searchView.text)
                    searchView.hide()
                    Toast.makeText(this@ContactActivity, searchView.text, Toast.LENGTH_SHORT).show()
                    false
                }
        }
    }


}