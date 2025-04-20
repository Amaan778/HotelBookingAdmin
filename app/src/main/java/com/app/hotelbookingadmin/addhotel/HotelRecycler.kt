package com.app.hotelbookingadmin.addhotel

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.hotelbookingadmin.R
import com.bumptech.glide.Glide

class HotelRecycler(private val context: Context, private val list:List<HotelDataClass>) :RecyclerView.Adapter<HotelRecycler.Viewholder>(){
    class Viewholder(itemview:View) :RecyclerView.ViewHolder(itemview) {
        val title=itemview.findViewById<TextView>(R.id.title)
        val address=itemview.findViewById<TextView>(R.id.addressdetail)
        val price=itemview.findViewById<TextView>(R.id.price)
        val offpercent=itemview.findViewById<TextView>(R.id.off)
        val rate=itemview.findViewById<TextView>(R.id.rates)
        val galler=itemview.findViewById<ImageView>(R.id.coverimg)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val view=LayoutInflater.from(context).inflate(R.layout.hotel,parent,false)
        return Viewholder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.title.text=list[position].hotelname
        holder.address.text=list[position].hoteladdress
        holder.price.text=list[position].hotelprice
        holder.offpercent.text=list[position].hoteloffer
        holder.rate.text=list[position].hotelrating
        Glide.with(context).load(list[position].coverimage).into(holder.galler)
    }
}