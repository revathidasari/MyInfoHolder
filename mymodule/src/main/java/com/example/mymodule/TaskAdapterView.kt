package com.example.mymodule

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mymodule.ui.home.HomeViewModel

class TaskAdapterView(
    var tasks : List<Task>,
    private val homeViewModel: HomeViewModel
) : RecyclerView.Adapter<TaskAdapterView.TaskViewHolder>() {

    private var count : Int = 0
    inner class TaskViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentItem = tasks[position]
        val taskMover = holder.itemView.findViewById<ImageButton>(R.id.taskMover)
        holder.itemView.apply {
            findViewById<TextView>(R.id.taskText).text = tasks[position].title
            findViewById<CheckBox>(R.id.taskCheck).isChecked = tasks[position].isChecked
        }

        taskMover.setOnLongClickListener {
            //add snack bar to undo the action
            Log.d("revathi","adapter " +currentItem)
            homeViewModel.delete(currentItem)
            notifyItemRemoved(position)
            false
        }

        if (holder.itemView.findViewById<CheckBox>(R.id.taskCheck).isChecked) {
            //getCheckedTasks(position)
        }
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    //to move the checked items to down as done with count
    fun getCheckedTasks(position: Int) : Int {
        tasks[position]
        count++
        return 0
    }
    //moveable/draggable implementation pending on this view
}