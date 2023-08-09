package com.example.contactlistroomdatabase

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ContactAdapter(private var contact:List<Contact>,
                     val context: Context,

                    var database: ContactDatabase
                     ):RecyclerView.Adapter<ContactAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView=
            LayoutInflater.from(parent.context).inflate(R.layout.contactrow,parent,false)

        return MyViewHolder(itemView)
    }


    override fun getItemCount(): Int {

        return  contact.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem:Contact=contact[position]

        holder.txt1.text= "${currentItem.firstName} ${currentItem.lastName}"
        holder.txt3.text="Phone Number: ${currentItem.phoneNumber}"
        holder.txtblood.text="Blood Group: ${currentItem.bloodGroup}"
        holder.txtdept.text="Dpeartment: ${currentItem.department}"
        holder.txthome.text="Home District: ${currentItem.homeDistrict}"
        holder.btndel.setOnClickListener {
       GlobalScope.launch {

           database.dao.deleteContact(currentItem)
       }
        }


    }
    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val txt1: TextView =itemView.findViewById(R.id.tvFirstName)
        val txt3: TextView =itemView.findViewById(R.id.tvPhoneNumber)
        val txthome:TextView=itemView.findViewById(R.id.tvHomeDistrict)
        val txtdept:TextView=itemView.findViewById(R.id.tvDepartment)
        val txtblood:TextView=itemView.findViewById(R.id.tvBloodGroup)
        val btndel:FloatingActionButton=itemView.findViewById(R.id.DeleteContact)
    }

}