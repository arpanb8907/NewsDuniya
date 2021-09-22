package com.example.newsduniya

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class newsadapter(private val listener:newitemclicked) : RecyclerView.Adapter<newsviewholder>() {

    private val items:ArrayList<news_data> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): newsviewholder {

        // layout inflater convert xml to views

        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_items,parent,false)
        val viewholder = newsviewholder(view)

        view.setOnClickListener{
            listener.onitemclick(items[viewholder.adapterPosition])
        }
        return viewholder
    }

    override fun onBindViewHolder(holder: newsviewholder, position: Int) {

        val curr = items[position]
        holder.title.text = curr.title
        holder.author.text = curr.author
        Glide.with(holder.itemView.context).load(curr.imageurl).into(holder.image)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updatednews(update:ArrayList<news_data>){
        items.clear()
        items.addAll(update)

        notifyDataSetChanged()
    }
}

class newsviewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    //val title: TextView = itemView.findViewById(R.id.textView)

    val title: TextView = itemView.findViewById(R.id.title)
    val image : ImageView = itemView.findViewById(R.id.image)
    val author : TextView = itemView.findViewById(R.id.author)

}

interface newitemclicked{



    fun onitemclick(item:news_data)

}