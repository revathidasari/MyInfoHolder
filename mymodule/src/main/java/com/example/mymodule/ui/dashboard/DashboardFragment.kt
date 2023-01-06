package com.example.mymodule.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymodule.*
import com.example.mymodule.databinding.FragmentDashboardBinding
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var currentNoteLabel = ""
    private lateinit var noteRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

         val noteDao : NoteDao = NoteDatabase.createAndGetNoteDBInstance(this.requireContext()).noteDao

        val textView: TextView = binding.textDashboard
        noteRecyclerView = binding.dashboardList
        val chipNotes : ChipGroup = binding.chipNotes.chipGroup
        val addNoteText : EditText = binding.addNoteText
        val addNoteButton : Button = binding.addNoteButton

        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
//        Log.d("revathi"," get all"+dashboardViewModel.getAllNotes(database))
        val adapter = NoteAdapterView(listOf())

        noteRecyclerView.layoutManager = LinearLayoutManager(context)
        noteRecyclerView.adapter = adapter//dashboardViewModel.noteAdapterView



        lifecycle.coroutineScope.launch {
            dashboardViewModel.getAllNotes(noteDao).collect() {
                adapter.notes = it
                adapter.notifyDataSetChanged()
            }
        }
/*            lifecycle.coroutineScope.launch {
                dashboardViewModel.getAllNotes(noteDao).collect() {

                  *//*  dashboardViewModel.noteAdapterView.notes = it
                    dashboardViewModel.noteAdapterView.notifyItemInserted(dashboardViewModel.noteList.size-1)*//*
                }
            }*/


        chipNotes.setOnCheckedChangeListener { group, checkedId ->
          //  if (!checkedId.equals(group.checkedChipId)) {
                val chip: Chip = group.findViewById(checkedId)
                chip.let { chipView ->
                    currentNoteLabel = chip.text.toString()
                } ?: kotlin.run {

                }
            //}
        }

        addNoteButton.setOnClickListener {
            val description : String = addNoteText.text.toString()
            val note = Note(currentNoteLabel, description, false)

            dashboardViewModel.addAndSaveNote(note, noteDao)
           /* dashboardViewModel.noteList.add(note)
            dashboardViewModel.noteAdapterView.notifyItemInserted( //recycler view will update whole items even the items didnt changed
                dashboardViewModel.noteList.size-1
            )*/
            addNoteText.text.clear()
            //to select first chip as default chip
            chipNotes.findViewById<Chip>(R.id.personalChip).isChecked =true
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}