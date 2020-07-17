package com.example.recyclerviewbasics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ContactAdapterListener {

    private val adapter = ContactRecyclerViewAdapter(listener = this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layoutmanager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        contact_recycler_view.layoutManager = layoutmanager

        val contacts = mutableListOf<DisplayItem>()
        for (index in 0..50) {
            val contact = Contact(firstname = "Nut$index", lastname = "Van", phone = "$index")
            contacts.add(contact)
        }

        adapter.items = contacts
        contact_recycler_view.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun onContactClick(contact: Contact) {
        println("contact selected: {$contact}")
    }
}

data class Advertisement(
    val title: String,
    override val type: DisplayItem.ItemTypes = DisplayItem.ItemTypes.ADVERTISEMENT
): DisplayItem

data class Contact(
    val firstname: String,
    val lastname: String,
    val phone: String,
    override val type: DisplayItem.ItemTypes = DisplayItem.ItemTypes.CONTACT
): DisplayItem

data class Footer(
    val title: String,
    val description: String,
    override val type: DisplayItem.ItemTypes = DisplayItem.ItemTypes.FOOTER
): DisplayItem

interface DisplayItem {
    val type: ItemTypes

    enum class ItemTypes(val value: Int) {
        ADVERTISEMENT(0), CONTACT(1), FOOTER(2)
    }
}