package com.example.entryexit

import androidx.appcompat.app.AppCompatActivity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.text.SimpleDateFormat
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*


class MainActivity : AppCompatActivity() {

    private var name:EditText?=null;
    private var phone:EditText?=null
    private var address:EditText?=null;
    private var email:EditText?=null
    private var host_name:EditText?=null;
    private var host_phone:EditText?=null;
    private var host_email:EditText?=null
    private var doc:String?=null;
    private var cidatetime:String?=null
    private var button:Button?=null;
    private var db=FirebaseFirestore.getInstance();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        name=findViewById(R.id.name)
        phone=findViewById(R.id.phone)
        address=findViewById(R.id.address)
        email=findViewById(R.id.email)
        host_phone=findViewById(R.id.host_phone)
        host_name=findViewById(R.id.host_name)
        host_email=findViewById(R.id.host_email)
        button=findViewById(R.id.confirm)

        val cal = Calendar.getInstance()
        val date = cal.getTime()
        val dateFormat = SimpleDateFormat("HH:mm:ss")
        cidatetime = dateFormat.format(date)

        button?.setOnClickListener {
            if (name?.text!!.isNotEmpty() && phone?.text!!.isNotEmpty() && address?.text!!.isNotEmpty() && host_name?.text!!.isNotEmpty() && email?.text!!.isNotEmpty() && host_phone?.text!!.isNotEmpty() && host_email?.text!!.isNotEmpty()) {
                create_record()
                var sms = "$name \n $phone \n $email \n $cidatetime"
                var emailtext = "$name \n $phone \n $email \n $cidatetime"
                sendSMS(sms)
                sendEmail(emailtext)
            }
            else {
                Toast.makeText(baseContext,"please enter details!", Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun sendSMS(SMSText: String){
        // To send messages, access to premium API like Twilio is needed.
        // It can be implemented and used here.
    }

    private fun sendEmail(EmailText: String){
        // To send emails, access to premium features of Firebase is needed.
        // It can be implemented and used here.
    }

    private fun create_record(){
        val record = hashMapOf(
                "name" to name?.text.toString(),
                "phone" to phone?.text.toString(),
                "address" to address?.text.toString(),
                "email" to email?.text.toString(),
                "host name" to host_name?.text.toString(),
                "checkin date time" to cidatetime,
                "host email" to host_email?.text.toString(),
                "host phone" to host_phone?.text.toString()
        )

        db.collection("visitor")
                .add(record)
                .addOnSuccessListener{ u ->

                    doc=u.id
                    Toast.makeText(baseContext,"successfully written!", Toast.LENGTH_LONG).show()
                    val intent= Intent(this,details::class.java)
                    intent.putExtra("id",doc)
                    startActivity(intent)
                    finish()
                }
                .addOnFailureListener { Toast.makeText(baseContext,"Error writing document", Toast.LENGTH_LONG).show() }
    }



}
