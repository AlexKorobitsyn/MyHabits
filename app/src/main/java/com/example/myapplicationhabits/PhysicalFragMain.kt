package com.example.myapplicationhabits

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationhabits.databinding.FragmentPhysicalFragMainBinding

class PhysicalFragMain : Fragment() {
    private val dataModel: HabitsModel by activityViewModels()
    private var yourArrayList: ArrayList<Habit> = ArrayList<Habit>()
    lateinit var binding:FragmentPhysicalFragMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPhysicalFragMainBinding.inflate(inflater)
        setupRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataModel.habitList.observe(viewLifecycleOwner, { habits ->
            dataModel.habitList.value?.let { yourArrayList = it }
            setupRecyclerView()
        })
    }
    private fun setupRecyclerView() {
        val filteredList = yourArrayList.filter { it.type == "Физическая" }
        val listView: RecyclerView = binding.listMain
        println("call for EMotional main ${yourArrayList}")
        listView.hasFixedSize()
        listView.layoutManager = LinearLayoutManager(activity)
        listView.adapter = activity?.let { MyAdapter(filteredList, it, "Физическая") }
    }
    companion object {
        @JvmStatic
        fun newInstance() = PhysicalFragMain()
    }
}