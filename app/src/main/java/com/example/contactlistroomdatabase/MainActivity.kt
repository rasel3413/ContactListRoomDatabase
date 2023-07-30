package com.example.contactlistroomdatabase

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Window
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       val database:ContactDatabase = Room.databaseBuilder(
            applicationContext,ContactDatabase::class.java,
            "contactDB"

        ).build()

        var contat= mutableListOf<Contact>()

        lifecycleScope.launch{
            database.dao.getContactsOrderByFirstName().collect(){contact->
              contat.clear()
                contat.addAll(contact)
                val recylce=findViewById<RecyclerView>(R.id.recycler)
                recylce.layoutManager=LinearLayoutManager(this@MainActivity)
                recylce.adapter=ContactAdapter(contat,this@MainActivity)

            }
        }




        val btn=findViewById<ImageButton>(R.id.btnAddContact)

        btn.setOnClickListener {
           val dialog=Dialog(this)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.pop)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            val animation = AnimationUtils.loadAnimation(this, R.anim.slide_up)
            dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
            dialog.window?.setGravity(Gravity.CENTER)
            val btndel =dialog.findViewById<ImageButton>(R.id.btndel)
            btndel.setOnClickListener {
                dialog.dismiss()
            }

            val btnAdd=dialog.findViewById<Button>(R.id.btnadd)
            val firstName=dialog.findViewById<EditText>(R.id.edFirst)
            val lastName=dialog.findViewById<EditText>(R.id.edSecond)
            val phoneNumber=dialog.findViewById<EditText>(R.id.edPhoneNumber)

            btnAdd.setOnClickListener {
                val first:String=firstName.text.toString()
                val second:String=lastName.text.toString()
                val phone:String=phoneNumber.text.toString()
                GlobalScope.launch {
                    database.dao.insertContact(Contact(first,second,phone))
                }
                firstName.text.clear();
                lastName.text.clear()
                phoneNumber.text.clear()
                dialog.dismiss()

            }
            dialog.show()
        }


    }
}