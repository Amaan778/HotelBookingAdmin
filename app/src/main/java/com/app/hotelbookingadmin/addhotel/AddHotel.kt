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
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.yalantis.ucrop.UCrop
import java.io.File
import java.util.UUID

class AddHotel : AppCompatActivity() {
    private lateinit var coverimg:ImageView
    private lateinit var coverbtn:Button
    private lateinit var addhotelbtn:Button
    private lateinit var title:EditText
    private lateinit var address:EditText
    private lateinit var rating:EditText
    private lateinit var off:EditText
    private lateinit var pricing:EditText
    private var coverImageUri: Uri? = null // Cover image URI
    private lateinit var storage: FirebaseStorage
    private lateinit var firestore: FirebaseFirestore

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

        firestore = FirebaseFirestore.getInstance()
        storage = FirebaseStorage.getInstance()

        coverbtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, REQUEST_IMAGE_PICK)
        }

        addhotelbtn.setOnClickListener {

            val hotelname=title.text.toString()
            val hoteladdress=address.text.toString()
            val hotelrate=rating.text.toString()
            val hoteloffer=off.text.toString()
            val hotelprice=pricing.text.toString()

            Toast.makeText(this,"button clicked",Toast.LENGTH_LONG).show()

            if (hotelname.isEmpty()){
                title.error="Required"
            }
            else if (hoteladdress.isEmpty()){
                address.error="Required"
            }
            else if (hotelrate.isEmpty()){
                rating.error="Required"
            }
            else if (hoteloffer.isEmpty()){
                off.error="Required"
            }
            else if (hotelprice.isEmpty()){
                pricing.error="Required"
            }

            else{

                val data= hashMapOf(
                    "hotelname" to hotelname,
                    "hoteladdress" to hoteladdress,
                    "hotelrating" to hotelrate,
                    "hoteloffer" to hoteloffer,
                    "hotelprice" to hotelprice
                )

//                val uuid=UUID.randomUUID().toString()
//                data["id"]=uuid
//
////                            here implement the data for title description and etc,
//                Toast.makeText(this,"Data Uploading Started",Toast.LENGTH_LONG).show()
//                savetofirebase(data,uuid)

                coverImageUri?.let {uri->
                    val coverfilename="cover_${System.currentTimeMillis()}.jpg"
                    val storageref=storage.reference.child("images/$coverfilename")
                    storageref.putFile(uri).addOnSuccessListener {
                        storageref.downloadUrl.addOnSuccessListener {coverurl->
//                            val uuid=UUID.randomUUID().toString()
                            data["coverimage"]=coverurl.toString()
//                            data["id"]=uuid

//                            here implement the data for title description and etc,
                            Toast.makeText(this,"Data Uploading Started",Toast.LENGTH_LONG).show()
                            savetofirebase(data,"ii")


                        }.addOnFailureListener {
                            Toast.makeText(this,"error",Toast.LENGTH_LONG).show()
                        }
                    } ?: run {
                        Toast.makeText(this, "Please select a cover image.", Toast.LENGTH_SHORT).show()
                    }

                }

            }

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

    private fun savetofirebase(data:HashMap<String, String> , uid:String){

        firestore.collection("AllHotels").document(uid)
            .set(data)
            .addOnSuccessListener {
                resetform()
                Toast.makeText(this,"Data Uploaded Suceessfully",Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener {
                Toast.makeText(this,"Data was not Uploaded",Toast.LENGTH_LONG).show()
            }

    }

    private fun resetform(){
        title.text.clear()
        address.text.clear()
        rating.text.clear()
        off.text.clear()
        pricing.text.clear()

        coverImageUri=null
        coverimg.setImageResource(R.drawable.hotelsplash)
    }

}