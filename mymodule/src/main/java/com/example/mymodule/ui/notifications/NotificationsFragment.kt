package com.example.mymodule.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymodule.TrackMonthlyDatabase
import com.example.mymodule.TrackMonthlyRepository
import com.example.mymodule.TrackMonthlyViewModel
import com.example.mymodule.TrackerAdapter
import com.example.mymodule.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

   /*     //db changes
        val database = TrackMonthlyDatabase.getInstance(context = this.requireContext())
        val repository = TrackMonthlyRepository(database)
        val factory = TrackMonthlyViewModel(repository)

        val viewModelT = ViewModelProvider(this).get(TrackMonthlyViewModel::class.java)
*/
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textNotifications
        notificationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        val trackRecyclerView : RecyclerView = binding.notifList
        trackRecyclerView.adapter = notificationsViewModel.trackAdapterView
        trackRecyclerView.layoutManager = LinearLayoutManager(context)

        return root
    }

    fun specificMonthView(monthTitle: String) {
      //  TrackerAdapter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}