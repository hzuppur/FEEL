package com.example.feel.ui.viewFeelings.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.feel.R
import com.example.feel.data.Feeling
import kotlinx.android.synthetic.main.custom_row.view.*
import java.util.*

class ListAdapter(private val cellClickListener: CellClickListener) :
    RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var feelingList = emptyList<Feeling>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = feelingList[position]

        val sdf = java.text.SimpleDateFormat("d. MMMM", Locale.getDefault())
        val date = Date(currentItem.feeling_time)

        holder.itemView.when_txt.text = sdf.format(date)
        holder.itemView.emotion_txt.text = currentItem.feeling_type

        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return feelingList.size
    }

    fun setData(feeling: List<Feeling>) {
        this.feelingList = feeling
        notifyDataSetChanged()
    }
}
