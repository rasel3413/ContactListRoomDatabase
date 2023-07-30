package com.example.contactlistroomdatabase

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(private  val contact:List<Contact>,val context: Context):RecyclerView.Adapter<ContactAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView=
            LayoutInflater.from(parent.context).inflate(R.layout.contactrow,parent,false)

        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return  contact.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem=contact[position]

        holder.txt1.text= "${currentItem.firstName} ${currentItem.lastName}"
        holder.txt3.text=currentItem.phoneNumber


    }
    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val txt1: TextView =itemView.findViewById(R.id.tvFirstName)
        val txt3: TextView =itemView.findViewById(R.id.tvPhoneNumber)
    }

}