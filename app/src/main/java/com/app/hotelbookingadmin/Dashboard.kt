package com.app.hotelbookingadmin

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.app.hotelbookingadmin.addhotel.AddHotel
import com.app.hotelbookingadmin.addhotel.HotelDashboard
import com.app.hotelbookingadmin.location.LocationActivity
import com.app.hotelbookingadmin.login.LoginDetails
import com.app.hotelbookingadmin.notification.NotificationActivity

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
        slider=findViewById(R.id.slider)
        notification=findViewById(R.id.notification)
        feedback=findViewById(R.id.feedback)
        login=findViewById(R.id.login)

        manageallservice.setOnClickListener {
            startActivity(Intent(this,HotelDashboard::class.java))
        }

        login.setOnClickListener {
            startActivity(Intent(this,LoginDetails::class.java))
        }

        slider.setOnClickListener {
            startActivity(Intent(this, LocationActivity::class.java))
        }

        notification.setOnClickListener {
            startActivity(Intent(this,NotificationActivity::class.java))
        }

    }
}