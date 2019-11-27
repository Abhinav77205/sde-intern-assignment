package com.example.entryexit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_details.*
import java.text.SimpleDateFormat
import java.util.*

class details : AppCompatActivity() {

    private var db= FirebaseFirestore.getInstance();
    private var nametext:TextView?=null
    private var phonetext:TextView?=null
    private var addresstext:TextView?=null
    private var hostnametext:TextView?=null
    private var emailtext:TextView?=null
    private var button1: Button?=null;
    private var codatetime:String?=null


    private var hostemailtext:TextView?=null
    private var hostphonetext:TextView?=null
    private var id:String=""
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        id=intent?.getStringExtra("id").toString()

        nametext=findViewById(R.id.nametext)
        addresstext=findViewById(R.id.addresstext)
        phonetext=findViewById(R.id.phonetext)
        hostnametext=findViewById(R.id.hostnametext)
        emailtext=findViewById(R.id.emailtext)
        hostemailtext=findViewById(R.id.hostemailtext)
        hostphonetext=findViewById(R.id.hostphonetext)
        button1=findViewById(R.id.checkout)

        get_record()

        val cal = Calendar.getInstance()
        val date = cal.getTime()
        val dateFormat = SimpleDateFormat("HH:mm:ss", Locale.ENGLISH)
        codatetime = dateFormat.format(date)

        button1?.setOnClickListener{
            check_out_time()
            var emailpass = "$nametext \n $phonetext \n $emailtext \n $codatetime \n$hostemailtext \n$addresstext"
            sendEmail(emailpass)

        }


    }
    private fun sendEmail(EmailText: String){
        // To send emails, access to premium features of Firebase is needed.
        // It can be implemented and used here.
    }
    private fun check_out_time(){
        val record = hashMapOf(
                "checkout time" to codatetime
        )

        db.collection("visitor")
                .document(id)
                .update("checkout time", codatetime)
                .addOnSuccessListener{ u ->

                    Toast.makeText(baseContext,"successfully checked out!", Toast.LENGTH_LONG).show()
                    val intent= Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                .addOnFailureListener { Toast.makeText(baseContext,"Error writing document", Toast.LENGTH_LONG).show() }
    }

    private fun get_record(){
        val docRef = db.collection("visitor").document(id)
        docRef.get()
                .addOnSuccessListener { document ->
                    if (document != null) {

                        nametext?.setText(document.get("name").toString())
                        phonetext?.setText(document.get("phone").toString())
                        emailtext?.setText(document.get("email").toString())
                        addresstext?.setText(document.get("address").toString())
                        hostnametext?.setText(document.get("host name").toString())
                        hostemailtext?.setText(document.get("host email").toString())
                        hostphonetext?.setText(document.get("host phone").toString())



                    } else {
                        Toast.makeText(baseContext,"please enter details", Toast.LENGTH_LONG).show()
                    }
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(baseContext,"data not found", Toast.LENGTH_LONG).show()

                }
    }
}
