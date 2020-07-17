package com.example.recyclerviewbasics

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

interface ContactAdapterListener {
    fun onContactClick(contact: Contact)
}

class ContactRecyclerViewAdapter(val listener: ContactAdapterListener): RecyclerView.Adapter<ContactRecyclerViewAdapter.ItemViewHolder>() {

    open class ItemViewHolder(open val view: View): RecyclerView.ViewHolder(view)
    class AdvertisementViewHolder(override val view: View): ItemViewHolder(view)
    class ContactViewHolder(override val view: View): ItemViewHolder(view)
    class FooterViewHolder(override val view: View): ItemViewHolder(view)

    var items: MutableList<DisplayItem> = mutableListOf()

    override fun getItemViewType(position: Int): Int {
        return items[position].type.value
    }

    /*
    กำหนด xml/view ให้กับ View Holder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return when (viewType) {
            DisplayItem.ItemTypes.ADVERTISEMENT.value -> {
                val view = LayoutInflater.from(parent.context).inflate(
                    R.layout.view_holder_item,
                    parent,
                    false
                )
                AdvertisementViewHolder(view)
            }
            DisplayItem.ItemTypes.CONTACT.value -> {
                val view = LayoutInflater.from(parent.context).inflate(
                    R.layout.view_holder_item,
                    parent,
                    false
                )
                ContactViewHolder(view)
            }
            DisplayItem.ItemTypes.FOOTER.value -> {
                val view = LayoutInflater.from(parent.context).inflate(
                    R.layout.view_holder_item,
                    parent,
                    false
                )
                FooterViewHolder(view)
            }
            else -> ItemViewHolder(View(parent.context))
        }
    }

    override fun getItemCount(): Int = items.count()

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        when (holder) {
            is AdvertisementViewHolder -> { }
            is ContactViewHolder -> {
                val nameTextView: TextView = holder.view.findViewById(R.id.name_text_view)
                val phoneTextView: TextView = holder.view.findViewById(R.id.phone_text_view)

                val contact = items[position] as Contact
                nameTextView.text = contact.firstname + " " + contact.lastname
                phoneTextView.text = contact.phone

                holder.view.setOnClickListener {
                    listener.onContactClick(contact)
                }
            }
            is FooterViewHolder -> { }
        }
    }

}