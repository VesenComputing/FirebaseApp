package com.vesencom.firebasesignupsignin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_logged_in.*

class LoggedInActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    lateinit var database: DatabaseReference

    private var fUserId: String = " "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged_in)


        auth = Firebase.auth
        val email = auth.currentUser!!.email
        textViewEmailAddress.setText(email)

        buttonLogout.setOnClickListener {
            textViewEmailAddress.setText("")

            auth.signOut()
            startActivity(Intent(this, LogInActivity::class.java))
        }
    }
}