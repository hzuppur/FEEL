package com.example.feel.ui.understand

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.feel.R
import com.example.feel.ui.viewFeelings.list.ListAdapter
import kotlinx.android.synthetic.main.understand_text_row.view.*

class UnderstandListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var textList = emptyList<String>()

    override fun getItemCount(): Int {
        return textList.size
    }

    fun setData(texts: List<String>) {
        this.textList = texts
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ListAdapter.MyViewHolder, position: Int) {
        val currentItem = textList[position]
        if (currentItem.startsWith("”")) {
            holder.itemView.row_txt.setTypeface(null, Typeface.ITALIC)
        } else {
            holder.itemView.row_txt.setTypeface(null, Typeface.NORMAL)
        }
        holder.itemView.row_txt.text = currentItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.MyViewHolder {
        return ListAdapter.MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.understand_text_row, parent, false)
        )
    }


}