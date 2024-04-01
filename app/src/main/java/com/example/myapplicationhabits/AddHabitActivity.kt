package com.example.myapplicationhabits

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.get
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson

class AddHabitActivity : AppCompatActivity() {
    private val dataModel: HabitsModel by viewModels()
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {

        lateinit var habitNameEditText: EditText
        lateinit var descriptionEditText: EditText
        lateinit var prioritySpinner: Spinner
        lateinit var habitTypeRadioGroup: RadioGroup
        lateinit var frequencyEditText: EditText
        lateinit var periodicityEditText: EditText
        lateinit var saveButton: Button
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        val a:LinearLayout = findViewById(R.id.main)
//        a.setBackgroundColor(Color.parseColor("#1D1B20"))
        setContentView(R.layout.addhabitactivity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.addhabit)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        habitNameEditText = findViewById(R.id.editTextHabitName)
        descriptionEditText = findViewById(R.id.editTextDescription)
        prioritySpinner = findViewById(R.id.spinnerPriority)
        habitTypeRadioGroup = findViewById(R.id.radioGroupHabitType)
        frequencyEditText = findViewById(R.id.editTextFrequency)
        periodicityEditText = findViewById(R.id.editTextPeriodicity)
        saveButton = findViewById(R.id.buttonSave)
        val cac = intent.getIntExtra("position", -1)
        println("cac $cac")
        saveButton.setOnClickListener {
            val checkedRadioButtonId = habitTypeRadioGroup.checkedRadioButtonId
            var res: String = ""
            res = when (checkedRadioButtonId) {
                -1 -> "Тип не выбран"
                else -> {
                    val selectedRadioButton = findViewById<RadioButton>(checkedRadioButtonId)
                    selectedRadioButton.text.toString()
                }
            }
            val habit = Habit(
                habitNameEditText.text.toString(),
                descriptionEditText.text.toString(),
                prioritySpinner.selectedItemId.toString(),
                res,
                frequencyEditText.text.toString(),
                periodicityEditText.text.toString()
            )
            dataModel.addNewHabit(habit)
            dataModel.setHabits(habit)
            val prefs: SharedPreferences = getPreferences(Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = prefs.edit()
            val gson = Gson()
            val json: String = gson.toJson(habit)
            println(json)
            editor.putString("habit", json)
            editor.apply()

            // Закрываем активность после сохранения
            finish()
        }
    }
}

