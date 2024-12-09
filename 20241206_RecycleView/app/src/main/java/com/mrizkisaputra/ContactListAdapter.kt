package com.mrizkisaputra

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mrizkisaputra.databinding.ItemContactBinding
import com.mrizkisaputra.model.Contact

class ContactListAdapter(private val contacts: ArrayList<Contact>) : ListAdapter<Contact, ContactListAdapter.ViewHolder>(DIFF_CALLBACK) {
    class ViewHolder(itemView: ItemContactBinding) : RecyclerView.ViewHolder(itemView.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = getItem(position)
    }

    fun addMoreContacts(newContacts: List<Contact>) {
        contacts.addAll(newContacts)
        submitList(contacts) // DiffUtil takes care of the check
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Contact> = object : DiffUtil.ItemCallback<Contact>() {
            override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
//                return oldItem.id == newItem.id
                return false
            }

            override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
                return oldItem.name == newItem.name && oldItem.isOnline == newItem.isOnline
            }
        }
    }
}