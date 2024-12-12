package com.mrizkisaputra

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import androidx.navigation.findNavController
import com.mrizkisaputra.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), Toolbar.OnMenuItemClickListener {
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

        val homeFragment = HomeFragment()
        supportFragmentManager.commit {
            add(R.id.fragment_container_view, homeFragment, HomeFragment::class.simpleName)
        }

        binding.appBar.setOnMenuItemClickListener(this)
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_announcement -> {
                supportFragmentManager.commit {
                    replace(R.id.fragment_container_view, AnnouncementFragment())
                    addToBackStack(null)
                }
                return true
            }
            R.id.menu_settings -> {
                supportFragmentManager.commit {
                    replace(R.id.fragment_container_view, SettingFragment())
                    addToBackStack(null)
                }
                return true
            }
            R.id.menu_contact -> {
                Intent(this, ContactActivity::class.java).apply {
                    startActivity(this)
                }
            }
            R.id.menu_feedback -> {}
            else -> false
        }
        return true
    }


}