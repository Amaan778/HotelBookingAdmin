package com.app.hotelbookingadmin

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Dashboard : AppCompatActivity() {
    private lateinit var category:TextView
    private lateinit var manageallservice:TextView
    private lateinit var bookings:TextView
    private lateinit var slider:TextView
    private lateinit var notification:TextView
    private lateinit var feedback:TextView
    private lateinit var login:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        category=findViewById(R.id.category)
        manageallservice=findViewById(R.id.manageallservice)
        bookings=findViewById(R.id.bookings)

    }
}