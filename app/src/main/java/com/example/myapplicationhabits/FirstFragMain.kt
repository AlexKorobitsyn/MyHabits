package com.example.myapplicationhabits

import RedactHabitFragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.PersistableBundle
import android.view.Window
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.iterator
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationhabits.databinding.FragmentFirstFragMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FirstFragMain : Fragment() {

    private val dataModel: HabitsModel by activityViewModels()
    lateinit var binding:FragmentFirstFragMainBinding
    private var yourArrayList: ArrayList<Habit> = ArrayList<Habit>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        binding = FragmentFirstFragMainBinding.inflate(inflater)
        setupRecyclerView()
        println("ONCREATEVIEW!")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        loadHabitList()
//        loadHabitFromSharedPreferences() // Загружаем Habit из SharedPreferences
//        setupRecyclerView()
        dataModel.habitList.observe(viewLifecycleOwner, { habits ->
            println("HELP MEEEEEEEE!")
            dataModel.habitList.value?.let { yourArrayList = it }
            setupRecyclerView()
        })
        setupFloatingActionButton(view)
    }

    override fun onStart() {
        println("CheckSTart ${dataModel.habits.value}")
        super.onStart()
    }
    override fun onResume() {
        println("CheckRESUME ${dataModel.habits.value}")
        super.onResume()

//        println("resumeFIRSTFRAG")
//        loadHabitList()
//        loadHabitFromSharedPreferences()
//        setupRecyclerView()
    }
//    private fun loadHabitList() {
//        val prefs: SharedPreferences = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
//        val gson = Gson()
//        val json = prefs.getString(SHARED_KEY, null)
//        val type = object : TypeToken<ArrayList<Habit>>() {}.type
//        yourArrayList = gson.fromJson(json, type) ?: ArrayList()
//    }

//    private fun loadHabitFromSharedPreferences() {
//        val prefs: SharedPreferences = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
//        val gson = Gson()
//        val json = prefs.getString("habit", null)
//        println("loadHabit: "+ json)
//        val type = object : TypeToken<ArrayList<Habit>>() {}.type
//        val habits = gson.fromJson<ArrayList<Habit>>(json, type)
//        if (habits != null) {
//            yourArrayList.addAll(habits)
//
//            saveHabitList() // Сохраняем обновленный список
//            setupRecyclerView() // Обновляем RecyclerView
//        }
//    }
//    private fun saveHabitList() {//save
//        val prefs: SharedPreferences = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
//        val editor: SharedPreferences.Editor = prefs.edit()
//        val gson = Gson()
//        val json: String = gson.toJson(yourArrayList)
//        editor.putString(SHARED_KEY, json)
//        editor.apply()
//    }

    private fun setupRecyclerView() {
        val listView: RecyclerView = binding.listMain
        listView.hasFixedSize()
        listView.layoutManager = LinearLayoutManager(activity)
        listView.adapter = activity?.let { MyAdapter(yourArrayList, it) }
    }

    private fun setupFloatingActionButton(view: View) {
        binding.fab.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, RedactHabitFragment())
                .addToBackStack(null)
                .commit()
        }
    }
    companion object {

        @JvmStatic
        fun newInstance() = FirstFragMain()
    }
}