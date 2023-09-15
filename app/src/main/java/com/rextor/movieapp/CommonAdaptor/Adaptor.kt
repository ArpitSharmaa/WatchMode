package com.rextor.movieapp.CommonAdaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rextor.movieapp.Model.title
import com.rextor.movieapp.R

class Adaptor(private val to_Detail_Screen: (String) -> Unit) : ListAdapter<title, Adaptor.ViewHolder>(DIFF_CALLBACK) {
   inner class ViewHolder (view:View):RecyclerView.ViewHolder(view){
        val titleView = view.findViewById<TextView>(R.id.title_of)
        val year = view.findViewById<TextView>(R.id.year_of_title)
        init {
            itemView.setOnClickListener {
                to_Detail_Screen.invoke(getItem(adapterPosition).id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context).inflate(R.layout.list_layout,parent,false)
        return ViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
       with(holder){
           titleView.text = item.title
           year.text= item.year.toString()
       }
    }
    companion object{
        val DIFF_CALLBACK= object : DiffUtil.ItemCallback<title>(){
            override fun areItemsTheSame(oldItem: title, newItem: title): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: title, newItem: title): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }

}