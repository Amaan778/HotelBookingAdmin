package com.app.hotelbookingadmin.addhotel

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.app.hotelbookingadmin.R
import com.yalantis.ucrop.UCrop
import java.io.File

class AddHotel : AppCompatActivity() {
    private lateinit var coverimg:ImageView
    private lateinit var coverbtn:Button
    private lateinit var addhotelbtn:Button
    private lateinit var title:EditText
    private lateinit var address:EditText
    private lateinit var rating:EditText
    private lateinit var off:EditText
    private lateinit var pricing:EditText

    companion object {
        private const val REQUEST_IMAGE_PICK = 100
    }


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

        coverbtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, REQUEST_IMAGE_PICK)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_PICK && resultCode == Activity.RESULT_OK) {
            data?.data?.let { uri ->
                val destinationUri = Uri.fromFile(File(cacheDir, "cropped_image.jpg"))

                UCrop.of(uri, destinationUri)
                    .withAspectRatio(1f, 1f) // Set aspect ratio (e.g., 1:1 for square crop)
                    .withMaxResultSize(500, 500) // Set max resolution
                    .start(this)
            }
        } else if (requestCode == UCrop.REQUEST_CROP && resultCode == Activity.RESULT_OK) {
            val resultUri = UCrop.getOutput(data!!)
            resultUri?.let {
                coverimg.setImageURI(it) // Set cropped image to ImageView
            }
        } else if (resultCode == UCrop.RESULT_ERROR) {
            val cropError = UCrop.getError(data!!)
            Toast.makeText(this, "Crop Error: ${cropError?.message}", Toast.LENGTH_SHORT).show()
        }
    }


}