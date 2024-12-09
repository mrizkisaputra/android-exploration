package com.mrizkisaputra

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.mrizkisaputra.databinding.ActivityMainBinding
import com.mrizkisaputra.model.Contact

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

        binding.apply {
            val contacts = Contact.createContact(3)
            val contactAdapter = ContactAdapter(contacts)
            contactAdapter.setOnButtonMessageClick(object : ContactAdapter.OnClickListener {
                override fun onClick(c: Contact) {
                    Toast.makeText(this@MainActivity, c.name, Toast.LENGTH_SHORT).show()
                }

            })

            contactAdapter.setOnItemClickListener(object : ContactAdapter.OnClickListener {
                override fun onClick(contact: Contact) {
                    Toast.makeText(this@MainActivity, "${contact.name} ${contact.isOnline}", Toast.LENGTH_SHORT).show()
                }

            })

            recycleViewContact.layoutManager = LinearLayoutManager(this@MainActivity)
            recycleViewContact.adapter = contactAdapter

            val itemDecoration: ItemDecoration = DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL)
            recycleViewContact.addItemDecoration(itemDecoration)


            buttonAddContact.setOnClickListener {
                val contact = Contact("Person", true)
                contacts.add(contact)
                contactAdapter.notifyItemInserted(0)
                recycleViewContact.scrollToPosition(0)
            }

        }
    }


}