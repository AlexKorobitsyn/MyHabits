package com.example.myapplicationhabits

import android.annotation.SuppressLint
import android.content.Intent
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
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.get
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AddHabitActivity : AppCompatActivity() {

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
        saveButton.setOnClickListener{
            val intent:Intent = Intent(this, MainActivity::class.java)
            val checkedRadioButtonId = habitTypeRadioGroup.checkedRadioButtonId
            var res:String = ""
            res = when(checkedRadioButtonId){
                -1 -> {
                    "Тип не выбран"
                }

                else -> {
                    // Найдём переключатель по его id
                    val selectedRadioButton = findViewById<RadioButton>(checkedRadioButtonId)
                    selectedRadioButton.text.toString()
                }
            }

            intent.putExtra("position", cac)
            intent.putExtra("habitname", habitNameEditText.text.toString())
            intent.putExtra("habitinfo", descriptionEditText.text.toString())
            intent.putExtra("habitpriority", prioritySpinner.selectedItemId.toString())
            intent.putExtra("habitType", res )
            intent.putExtra("habitfreq", frequencyEditText.text.toString())
            intent.putExtra("habitperiodic", periodicityEditText.text.toString())
            startActivity(intent)
        }
    }
}