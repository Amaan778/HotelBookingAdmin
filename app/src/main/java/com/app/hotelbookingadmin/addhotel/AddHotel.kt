package com.app.hotelbookingadmin.addhotel

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.app.hotelbookingadmin.R

class AddHotel : AppCompatActivity() {
    private lateinit var coverimg:ImageView
    private lateinit var coverbtn:Button
    private lateinit var addhotelbtn:Button
    private lateinit var title:EditText
    private lateinit var address:EditText
    private lateinit var rating:EditText
    private lateinit var off:EditText
    private lateinit var pricing:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_hotel)

        coverimg=findViewById(R.id.coverimg)
        coverbtn=findViewById(R.id.coverbtn)
        addhotelbtn=findViewById(R.id.gallerybtn)
        title=findViewById(R.id.title)
        address=findViewById(R.id.address)
        rating=findViewById(R.id.rating)
        off=findViewById(R.id.off)
        pricing=findViewById(R.id.pricing)


    }
}