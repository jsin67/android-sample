package com.costa.jaggratsample.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.costa.jaggratsample.R
import com.costa.jaggratsample.models.Venue

class PlaceAdapter : RecyclerView.Adapter<PlaceAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val txtName = itemView.findViewById<TextView>(R.id.tv_post_title)
    }

    private val placeList = mutableListOf<Venue>()

    override fun getItemCount(): Int = placeList.size

    fun setVenueData(list: List<Venue>) {
        placeList.clear()
        placeList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtName?.text = placeList[position].name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(v);
    }
}