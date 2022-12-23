package com.example.mymodule.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymodule.Task
import com.example.mymodule.databinding.FragmentHomeBinding
import com.example.mymodule.util.SharedPref
import com.example.mymodule.util.WriteAndReadFileData
import java.util.Locale

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var writeAndReadFileData = WriteAndReadFileData()
    //  Caused by: androidx.fragment.app.Fragment$InstantiationException:
    //  Unable to instantiate fragment com.example.mymodule.ui.home.HomeFragment: calling Fragment constructor caused an exception
//    private var sharedPref = SharedPref(this.requireContext())


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        val taskRecyclerView : RecyclerView = binding.homeList
        val addTaskText : EditText = binding.addTaskText
        val addTaskButton : Button = binding.addTaskButton

        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        taskRecyclerView.adapter = homeViewModel.taskAdapterView
        taskRecyclerView.layoutManager = LinearLayoutManager(context)

        addTaskButton.setOnClickListener {
            val title: String = addTaskText.text.toString()
            val task = Task(title, false)
            homeViewModel.taskList.add(task)
            homeViewModel.taskAdapterView.notifyItemInserted(
                homeViewModel.taskList.size -1
            )
            addTaskText.text.clear()



            //fileWritingAndReading()
           // setLocaleAndUpdate(title)

        }


        return root
    }

    // to read and write data into json & text file
    private fun fileWritingAndReading() {
        writeAndReadFileData.writeJSONData("json","written")
        writeAndReadFileData.readJSONData("json")

        writeAndReadFileData.writeTextData("text file")
        writeAndReadFileData.readTextData()
    }

    //to set the language from app and recreate to update
    private fun setLocaleAndUpdate(title: String) {
        val locale = Locale(title)
        val config = this.context?.resources?.configuration
        config?.locale = locale
        if (config != null) {
            this.context?.createConfigurationContext(config)
        }
        this.requireActivity().recreate()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}