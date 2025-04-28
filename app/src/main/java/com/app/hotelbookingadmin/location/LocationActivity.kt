package com.app.hotelbookingadmin.location

import android.Manifest
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.app.hotelbookingadmin.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class LocationActivity : AppCompatActivity() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val LOCATION_PERMISSION_REQUEST_CODE = 1

    private lateinit var btnLocation: Button
    private lateinit var tvLocation: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        btnLocation = findViewById(R.id.btnLocation)
        tvLocation = findViewById(R.id.tvLocation)

        btnLocation.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // If permission not granted, request it
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ),
                    LOCATION_PERMISSION_REQUEST_CODE
                )
            } else {
                // If permission already granted, get location
                getCurrentLocation()
            }
        }
    }

    private fun getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                if (location != null) {
                    val geocoder = Geocoder(this)
                    val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)

                    if (addresses != null && addresses.isNotEmpty()) {
                        val address = addresses[0]

                        val area = address.subLocality          // like Chembur, Airoli
                        val city = address.locality             // like Mumbai, Navi Mumbai
                        val state = address.adminArea           // like Maharashtra
                        val country = address.countryName       // like India
                        val fullAddress = address.getAddressLine(0) // Full Address (Optional)

                        tvLocation.text = """
                        Area: $area
                        City: $city
                        State: $state
                        Country: $country
                        
                        Full Address: $fullAddress
                    """.trimIndent()
                    } else {
                        tvLocation.text = "Location details not found"
                    }
                } else {
                    tvLocation.text = "Location not available"
                }
            }
    }

    // Handle permission result
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation()
            } else {
                tvLocation.text = "Permission denied"
            }
        }
    }
}