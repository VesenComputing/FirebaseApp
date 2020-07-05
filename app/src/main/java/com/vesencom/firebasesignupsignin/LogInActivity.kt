package com.vesencom.firebasesignupsignin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_log_in.*

class LogInActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    lateinit var database: DatabaseReference

    private var fUserId: String = " "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        auth = Firebase.auth

        buttonLogin.setOnClickListener {
            loginUser()
        }
        
        mvLogin.setOnClickListener { 
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun loginUser() {
//        get values
        val email = editTextTextEmail.text.toString()
        val password = editTextPassword.text.toString()

        if (!email.isEmpty() && !password.isEmpty()){

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {task ->
                    if (task.isSuccessful){
//                        sign in success
                        val user = auth.currentUser!!.uid

                        Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()

                        val intent = Intent(this, LoggedInActivity::class.java)
                        startActivity(intent)

                    }else{
                        // If sign in fails, display a message to the user.
                        Toast.makeText(baseContext, "Authentication failed." + task.exception,
                            Toast.LENGTH_SHORT).show()

                    }
                }
        }
    }
}