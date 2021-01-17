package com.jamestrada.chatwithjames

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_message_chat.*
import java.lang.StringBuilder

class MessageChatActivity : AppCompatActivity() {
    var userIDVisit: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_chat)

        intent = intent
//        userIDVisit = intent.getStringExtra("visit_id")

        send_message_btn.setOnClickListener {
            val message = text_message.text.toString()
            if (message == "") {
                Toast.makeText(this, "Please write a message", Toast.LENGTH_LONG).show()
            } else {
                sendMessageToUser()
            }
        }
    }

    private fun sendMessageToUser() {
        TODO("Not yet implemented")
    }
}