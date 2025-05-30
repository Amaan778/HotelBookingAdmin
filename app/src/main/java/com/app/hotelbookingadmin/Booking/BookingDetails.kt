package com.app.hotelbookingadmin.Booking

import android.os.Bundle
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.app.hotelbookingadmin.R

class BookingDetails : AppCompatActivity() {
    private lateinit var toolbar: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var relative:RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_details)

        toolbar=findViewById(R.id.toolbartextview)
        recyclerView=findViewById(R.id.recycler)
        relative=findViewById(R.id.relative)

    }
}