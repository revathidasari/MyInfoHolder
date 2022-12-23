package com.example.mymodule

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mymodule.ui.notifications.NotificationsViewModel

class TrackAdapterView(
    val tracks : List<Track>,
    private val notificationsViewModel: NotificationsViewModel
) : RecyclerView.Adapter<TrackAdapterView.TrackViewHolder>() {

    inner class TrackViewHolder(itemView : View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_track, parent, false)
        return TrackViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        val currentItemPosition = tracks[position]
        holder.itemView.apply {
            findViewById<TextView>(R.id.monthName).text = currentItemPosition.monthTitle
            findViewById<TextView>(R.id.monthTotal).text = currentItemPosition.totalAmount.toString()
            findViewById<ImageButton>(R.id.detailedMonth).setOnClickListener{
                val intent = Intent(this.context, FetchMonthlyDetails::class.java)
                val bundle = Bundle()
                bundle.putString("month", currentItemPosition.monthTitle)
                intent.putExtras(bundle)
                it.context.startActivity(intent, bundle)
                //notificationsViewModel.detailedMonthView(currentItemPosition.monthTitle)
//                FetchMonthlyDetails(this.context, currentItemPosition.monthTitle)

            }
        }
    }

    override fun getItemCount(): Int {
        return tracks.size
    }

}
