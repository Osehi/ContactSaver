package com.polish.firebasecontactapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.polish.firebasecontactapp.R
import com.polish.firebasecontactapp.helper.OnItemClickListener
import com.polish.firebasecontactapp.model.Contact

class ContactListAdapter(val contacts:ArrayList<Contact>, clickListener:OnItemClickListener): RecyclerView.Adapter<ContactListAdapter.ContactViewHolder>() {

    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val firstName = itemView.findViewById<TextView>(R.id.contact_list_item_first_name_tv)
        val lastName = itemView.findViewById<TextView>(R.id.contact_list_item_last_name_tv)
        val phoneNumber = itemView.findViewById<TextView>(R.id.contact_list_item_phone_number_tv)
        val firstLetter = itemView.findViewById<TextView>(R.id.contact_list_item_first_letter_tv)
        fun bind(contactItem:Contact){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        // inflate the viewholder
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_list_item, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.firstName.text = contacts[position].firstName.toString()
        holder.lastName.text = contacts[position].lastName.toString()
        holder.phoneNumber.text = contacts[position].phoneNumber.toString()
        holder.firstLetter.text = contacts[position].firstName?.first().toString()




    }

    override fun getItemCount(): Int {
        return contacts.size
    }

}