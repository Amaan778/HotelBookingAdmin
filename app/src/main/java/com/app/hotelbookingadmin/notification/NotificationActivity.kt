package com.app.hotelbookingadmin.notification

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.app.hotelbookingadmin.R

class NotificationActivity : AppCompatActivity() {
    private lateinit var toolbar:TextView
    private lateinit var recyler:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        toolbar=findViewById(R.id.toolbar)
        recyler=findViewById(R.id.recycler)

        toolbar.setOnClickListener {
            Toast.makeText(this,"Clicking",Toast.LENGTH_LONG).show()
        }

    }
}