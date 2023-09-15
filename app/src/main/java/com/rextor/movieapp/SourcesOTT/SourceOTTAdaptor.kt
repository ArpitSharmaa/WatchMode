package com.rextor.movieapp.SourcesOTT

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rextor.movieapp.Model.listOFOTT
import com.rextor.movieapp.R

class SourceOTTAdaptor (private val context: Context, private val listener :(String?)->Unit): ListAdapter<listOFOTT,SourceOTTAdaptor.ViewHolder>(DIFF_CALLBACK) {
    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val name = view.findViewById<TextView>(R.id.ott_name)
        val logo = view.findViewById<ImageView>(R.id.ott_logo)
        init {
            itemView.setOnClickListener {
                listener.invoke(getItem(adapterPosition).android_playstore_url)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.ott_item_layout,parent,false)
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
       with(holder){
           name.text = item.name
           Glide.with(context).load(item.logo_100px).into(logo)
       }
    }
    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<listOFOTT>(){
            override fun areItemsTheSame(oldItem: listOFOTT, newItem: listOFOTT): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: listOFOTT, newItem: listOFOTT): Boolean {
                return oldItem.name == newItem.name
            }

        }
    }
}