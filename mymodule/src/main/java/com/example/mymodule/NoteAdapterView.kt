package com.example.mymodule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapterView(
    var notes : List<Note>
) : RecyclerView.Adapter<NoteAdapterView.NoteViewHolder> () {

    inner class NoteViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note_label, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val noteRadioButton = holder.itemView.findViewById<RadioButton>(R.id.noteRadioButton)
        holder.itemView.apply { 
            findViewById<TextView>(R.id.notePoint).text= notes[position].title.plus(": "+notes[position].description)
            noteRadioButton.isChecked = notes[position].isSelected
        }/*
        if (noteRadioButton.isChecked) 
            deleteItem(notes[position])*/
    }

    private fun deleteItem(note: Note) {

    }

    override fun getItemCount(): Int {
        return notes.size
    }

}