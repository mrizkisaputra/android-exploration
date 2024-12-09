package com.mrizkisaputra.model

data class Contact(val name: String, val isOnline: Boolean) {
    companion object {
        fun createContact(numContact: Int): ArrayList<Contact> {
            val contacts = ArrayList<Contact>()

            for (i in 1..numContact) {
                val contact = Contact("Person $i", i <= numContact / 2)
                contacts.add(contact)
            }

            return contacts
        }
    }
}