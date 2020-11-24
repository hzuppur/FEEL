package com.example.feel.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.feel.R
import com.example.feel.data.Feeling
import kotlinx.android.synthetic.main.custom_row.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var feelingList = emptyList<Feeling>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = feelingList[position]
        holder.itemView.id_txt.text = currentItem.id.toString()
        holder.itemView.firstName_txt.text = currentItem.feeling_type
        holder.itemView.lastName_txt.text = currentItem.trigger
        holder.itemView.age_txt.text = currentItem.feeling_positivity.toString()
    }

    override fun getItemCount(): Int {
        return feelingList.size
    }

    fun setData(feeling: List<Feeling>){
        this.feelingList = feeling
        notifyDataSetChanged()
    }
}