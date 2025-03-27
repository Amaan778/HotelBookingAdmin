package com.app.hotelbookingadmin.addhotel

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.app.hotelbookingadmin.R

class AddHotel : AppCompatActivity() {
    private lateinit var coverimg:ImageView
    private lateinit var coverbtn:Button
    private lateinit var gallerybtn:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_hotel)

        coverimg=findViewById(R.id.coverimg)
        coverbtn=findViewById(R.id.coverbtn)
        gallerybtn=findViewById(R.id.gallerybtn)


    }
}