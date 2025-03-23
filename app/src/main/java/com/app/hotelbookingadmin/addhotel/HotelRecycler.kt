package com.app.hotelbookingadmin.addhotel

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class HotelRecycler(private val context: Context, private val list:List<HotelDataClass>) :RecyclerView.Adapter<HotelRecycler.Viewholder>(){
    class Viewholder(itemview:View) :RecyclerView.ViewHolder(itemview) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        TODO("Not yet implemented")
    }
}