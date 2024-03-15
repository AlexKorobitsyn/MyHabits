package com.example.myapplicationhabits

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Window
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.iterator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private companion object {
        const val SHARED_KEY = "SHARED_KEY"
    }
    private fun SaveData(list: ArrayList<Habit>){
        val prefs: SharedPreferences = this.getPreferences(Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = prefs.edit()
        val gson = Gson()
        val json: String = gson.toJson(list)
        editor.putString(SHARED_KEY, json)
        editor.apply()
    }
    private var yourArrayList: ArrayList<Habit> = ArrayList<Habit>()
    @SuppressLint("MissingInflatedId")
    val bundle :Bundle = Bundle()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        val a:LinearLayout = findViewById(R.id.main)
//        a.setBackgroundColor(Color.parseColor("#1D1B20"))
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val fabButton: FloatingActionButton = findViewById(R.id.fab)
        fabButton.setOnClickListener {
            val intent = Intent(this, AddHabitActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onResume() {
        val a = intent.getStringExtra("habitname")

        if(a != null){
            val habit:Habit = Habit(a,
                intent.getStringExtra("habitinfo"), intent.getStringExtra("habitpriority")
                , intent.getStringExtra("habitType"), intent.getStringExtra("habitfreq"), intent.getStringExtra("habitperiodic"))

            yourArrayList = getArrayList()
            println("otladka")
            val pos = intent.getIntExtra("position", -1)
            if (pos == -1) {
                yourArrayList.add(habit)
            }
            else{
                yourArrayList[pos] = habit
            }
//            onSaveInstanceState(bundle)

            // Сохранение yourArrayList в Intent
            intent.putExtra("habitArrayList", yourArrayList)

            val listView: RecyclerView = findViewById(R.id.listMain)
            listView.hasFixedSize()
            listView.layoutManager = LinearLayoutManager(this)
            listView.adapter = MyAdapter(yourArrayList, this)
            }
        SaveData(yourArrayList)

        super.onResume()
    }


    override fun onPause() {

//        onSaveInstanceState(bundle)
        super.onPause()
    }

    override fun onStop() {
        val bundle :Bundle = Bundle()
        onSaveInstanceState(bundle)
        super.onStop()
    }
    override fun onRestoreInstanceState(
        savedInstanceState: Bundle?,
        persistentState: PersistableBundle?
    ) {

        super.onRestoreInstanceState(savedInstanceState, persistentState)
    }

    private fun  getArrayList() : ArrayList<Habit>{
        val prefs: SharedPreferences = this.getPreferences(Context.MODE_PRIVATE)
        val gson = Gson()
        val json = prefs.getString(SHARED_KEY, null);
        val type = object : TypeToken<ArrayList<Habit>>() {}.type
        return gson.fromJson(json, type);
    }
    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        outState.putParcelableArrayList("moveList", yourArrayList)
        super.onSaveInstanceState(outState, outPersistentState)
    }
}