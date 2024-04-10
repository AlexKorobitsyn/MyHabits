package com.example.myapplicationhabits

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.Context
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationhabits.databinding.FragmentFirstFragMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FirstFragMain : Fragment() {

    private val dataModel: HabitsModel by activityViewModels()
    lateinit var binding:FragmentFirstFragMainBinding
    private var yourArrayList: ArrayList<Habit> = ArrayList<Habit>()

    private var listener: OnFabClickedListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFabClickedListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFabClickedListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        binding = FragmentFirstFragMainBinding.inflate(inflater)
        setupRecyclerView()
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataModel.habitList.observe(viewLifecycleOwner, { habits ->
            dataModel.habitList.value?.let { yourArrayList = it }
            setupRecyclerView()
        })
        val fabButton = view.findViewById<FloatingActionButton>(R.id.fab)
        fabButton.setOnClickListener {
            listener?.onFabClicked()
        }
    }
    interface OnFabClickedListener {
        fun onFabClicked()
    }

    private fun setupRecyclerView() {
        val filteredList = yourArrayList.filter { it.type == "Поведенческая" }
        val listView: RecyclerView = binding.listMain
        listView.hasFixedSize()
        listView.layoutManager = LinearLayoutManager(activity)
        listView.adapter = activity?.let { MyAdapter(filteredList, it, "Поведенческая") }
    }


    companion object {

        @JvmStatic
        fun newInstance() = FirstFragMain()
    }
}