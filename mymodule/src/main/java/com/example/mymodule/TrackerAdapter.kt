package com.example.mymodule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TrackerAdapter(
    var track : List<TrackMonthlyItems>
    //,    private val viewModel: TrackMonthlyViewModel
) : RecyclerView.Adapter<TrackerAdapter.TrackerViewHolder>() {

    inner class TrackerViewHolder(itemView : View) :RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_month_specific, parent, false)
        return TrackerViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrackerViewHolder, position: Int) {
        val currentPosition = track[position]
        holder.itemView.apply {
            holder.itemView.findViewById<TextView>(R.id.currentMonth).text = track[position].name
            holder.itemView.findViewById<TextView>(R.id.incomeAmount).text = track[position].incomeAmount.toString()
            holder.itemView.findViewById<TextView>(R.id.savingsAmount).text = track[position].savingsAmount.toString()
            holder.itemView.findViewById<TextView>(R.id.expensesAmount).text = track[position].expensesAmount.toString()
            holder.itemView.findViewById<TextView>(R.id.emiAmount).text = track[position].emiAmount.toString()
            holder.itemView.findViewById<TextView>(R.id.spendingsAmount).text = track[position].spendingsAmount.toString()
            holder.itemView.findViewById<TextView>(R.id.investmentAmount).text = track[position].investmentAmount.toString()
            holder.itemView.findViewById<TextView>(R.id.onFoodAmount).text = track[position].onFoodAmount.toString()
            holder.itemView.findViewById<TextView>(R.id.totalText).text = track[position].toString()
            
            /*holder.itemView.findViewById<TextView>(R.id.totalButton).setOnClickListener {
                viewModel.insertAndUpdate(currentPosition)
            }*/

        }
    }

    override fun getItemCount(): Int {
        return track.size
    }
}