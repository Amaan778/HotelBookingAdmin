package com.app.hotelbookingadmin

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Chat : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        // Open WhatsApp when this activity starts
        openWhatsApp()

        // Update the TextView if needed
        val textView = findViewById<TextView>(R.id.tvInfo)
        textView.text = "Opening WhatsApp..."
    }

    private fun openWhatsApp() {
        val phoneNumber = "919324503691" // Replace with your WhatsApp number (with country code)
        val uri = Uri.parse("https://wa.me/$phoneNumber")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        intent.setPackage("com.whatsapp") // Ensures only WhatsApp opens

        try {
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}