package com.mrizkisaputra

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.convertTo
import androidx.recyclerview.widget.RecyclerView
import com.mrizkisaputra.databinding.ItemContactBinding
import com.mrizkisaputra.model.Contact

class ContactAdapter(private val contacts: ArrayList<Contact>) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {
    private lateinit var onClickListener: OnClickListener

    inner class ContactViewHolder(private val binding: ItemContactBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindingData(contact: Contact) {
            binding.textContactName.text = contact.name
            binding.buttonMessage.text = if (contact.isOnline) "Message" else "Offline"
            binding.buttonMessage.setOnClickListener {
                onClickListener.onClick(contact)
            }
            binding.root.setOnClickListener{ onClickListener.onClick(contact) }
        }
    }

    interface OnClickListener {
        fun onClick(contact: Contact)
    }

    fun setOnItemClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    fun setOnButtonMessageClick(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(binding)
    }

    override fun getItemCount(): Int = contacts.size

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contacts[position]
        holder.bindingData(contact)
//        with(holder.binding) {
//            this.textContactName.text = contact.name
//            this.buttonMessage.text = if (contact.isOnline) "Message" else "Offline"
//        }
    }
}