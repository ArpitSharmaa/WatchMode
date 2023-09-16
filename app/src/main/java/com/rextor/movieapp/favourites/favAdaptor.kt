package com.rextor.movieapp.favourites


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rextor.movieapp.LocalStrorage.Model.favouriteEntity
import com.rextor.movieapp.Model.title
import com.rextor.movieapp.R

class favAdaptor(private val to_Detail_Screen: (List<String>) -> Unit) :
    ListAdapter<favouriteEntity, favAdaptor.ViewHolder>(DIFF_CALLBACK) {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleView = view.findViewById<TextView>(R.id.title_of)
        val year = view.findViewById<TextView>(R.id.year_of_title)

        init {
            itemView.setOnClickListener {
                to_Detail_Screen.invoke(
                    listOf(
                        getItem(adapterPosition).id,
                        getItem(adapterPosition).type
                    )
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater =
            LayoutInflater.from(parent.context).inflate(R.layout.list_layout, parent, false)
        return ViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (item.id.isNotBlank()) {
            with(holder) {
                titleView.text = item.original_title
                year.text = item.release_date
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<favouriteEntity>() {
            override fun areItemsTheSame(
                oldItem: favouriteEntity,
                newItem: favouriteEntity
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: favouriteEntity,
                newItem: favouriteEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }

}