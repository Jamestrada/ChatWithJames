package com.jamestrada.chatwithjames

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_register.*
import okhttp3.internal.Internal.instance

class RegisterActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var refUsers: DatabaseReference
    private var firebaseUserId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val toolbar: Toolbar = findViewById(R.id.toolbar_register)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Register"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true) // back button
        toolbar.setNavigationOnClickListener {
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        mAuth = FirebaseAuth.getInstance()
        register_btn.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        val username: String = username_register.text.toString()
        val email: String = email_register.text.toString()
        val password: String = password_register.text.toString()

        if (username == "") {
            Toast.makeText(this, "Please write a username", Toast.LENGTH_LONG).show()
        } else if (email == "") {
            Toast.makeText(this, "Please write an email", Toast.LENGTH_LONG).show()
        } else if (password == "") {
            Toast.makeText(this, "Please write a password", Toast.LENGTH_LONG).show()
        } else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    firebaseUserId = mAuth.currentUser!!.uid
                    refUsers = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUserId)

                    val userHashMap = HashMap<String, Any>()
                    userHashMap["uid"] = firebaseUserId
                    userHashMap["username"] = username
                    userHashMap["profile"] = "https://firebasestorage.googleapis.com/v0/b/chat-with-james.appspot.com/o/profile.png?alt=media&token=873ba4bb-04bc-4b80-bb0f-4f512b8eaefc"
                    userHashMap["cover"] = "https://firebasestorage.googleapis.com/v0/b/chat-with-james.appspot.com/o/cover.jpg?alt=media&token=3eecd759-f6b9-4953-b105-d0bc200ef29a"
                    userHashMap["status"] = "offline"
                    userHashMap["search"] = username.toLowerCase()
                    userHashMap["facebook"] = "https://m.facebook.com"
                    userHashMap["instagram"] = "https://m.instagram.com"
                    userHashMap["website"] = "https://www.google.com"

                    refUsers.setValue(userHashMap).addOnCompleteListener(this) {
                        if (it.isSuccessful) {
                            val intent = Intent(this, MainActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                            finish()
                        }
                    }
                } else {
                    Toast.makeText(this, "Error Message: " + it.exception!!.message.toString(), Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}