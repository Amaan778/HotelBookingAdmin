package com.app.hotelbookingadmin.login

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.app.hotelbookingadmin.R

class LoginDetails : AppCompatActivity() {
    private lateinit var gmailtextview:TextView
    private lateinit var passwordtextview:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_details)

        gmailtextview=findViewById(R.id.gmailtextview)
        passwordtextview=findViewById(R.id.passwordtextview)

    }
}