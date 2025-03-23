package com.app.hotelbookingadmin.addhotel

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.hotelbookingadmin.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.firestore

class HotelDashboard : AppCompatActivity() {
    private lateinit var btn:FloatingActionButton
    private lateinit var recycler:RecyclerView
    private lateinit var emptytext:TextView
    private var mList = ArrayList<HotelDataClass>()
    private lateinit var adapter: HotelRecycler
    var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_dashboard)

        btn=findViewById(R.id.add)
        recycler=findViewById(R.id.recycler)
        emptytext=findViewById(R.id.emptytext)

        btn.setOnClickListener {
            startActivity(Intent(this,AddHotel::class.java))
        }

        recycler.layoutManager=LinearLayoutManager(this)
        adapter=HotelRecycler(this,mList)
        recycler.adapter=adapter

        mList.clear()

        db=FirebaseFirestore.getInstance()
        db.collection("AllHotels").addSnapshotListener(object : EventListener<QuerySnapshot> {
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                if (error!=null){
                    Toast.makeText(this@HotelDashboard,"Error"+error.message.toString(),Toast.LENGTH_LONG).show()
                }

                for (dc :DocumentChange in value?.documentChanges!!){
                    if (dc.type == DocumentChange.Type.ADDED) {
                        mList.add(dc.document.toObject(HotelDataClass::class.java))
                    }
                }

                adapter?.notifyDataSetChanged()

                if (mList.isEmpty()){
                    emptytext.visibility=View.VISIBLE
                    recycler.visibility= View.GONE
                }
                else{
                    emptytext.visibility=View.GONE
                    recycler.visibility=View.VISIBLE
                }
            }

        })

    }
}