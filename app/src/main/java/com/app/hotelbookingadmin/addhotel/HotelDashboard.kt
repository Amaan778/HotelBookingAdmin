package com.app.hotelbookingadmin.addhotel

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.app.hotelbookingadmin.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HotelDashboard : AppCompatActivity() {
    private lateinit var btn:FloatingActionButton
    private lateinit var recycler:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_dashboard)
    }
}