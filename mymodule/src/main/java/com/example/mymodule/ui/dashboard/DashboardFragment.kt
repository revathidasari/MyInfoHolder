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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymodule.Note
import com.example.mymodule.R
import com.example.mymodule.databinding.FragmentDashboardBinding
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var currentNoteLabel = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        val noteRecyclerView : RecyclerView = binding.dashboardList
        val chipNotes : ChipGroup = binding.chipNotes.chipGroup
        val addNoteText : EditText = binding.addNoteText
        val addNoteButton : Button = binding.addNoteButton

        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        noteRecyclerView.adapter = dashboardViewModel.noteAdapterView
        noteRecyclerView.layoutManager = LinearLayoutManager(context)

        chipNotes.setOnCheckedChangeListener { group, checkedId ->
            val chip : Chip = group.findViewById(checkedId)
            chip.let { chipView ->
                currentNoteLabel = chip.text.toString()
            } ?: kotlin.run {

            }
        }

        addNoteButton.setOnClickListener {
            val description : String = addNoteText.text.toString()
            val note = Note(currentNoteLabel, description, false)

            dashboardViewModel.noteList.add(note)
            dashboardViewModel.noteAdapterView.notifyItemInserted(
                dashboardViewModel.noteList.size-1
            )
            addNoteText.text.clear()
            //to select first chip as default chip
            chipNotes.findViewById<Chip>(R.id.personalChip).isChecked =true
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}