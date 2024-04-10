import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.myapplicationhabits.FirstFragMain
import com.example.myapplicationhabits.Habit
import com.example.myapplicationhabits.HabitsModel
import com.example.myapplicationhabits.R
import com.example.myapplicationhabits.databinding.FragmentRedactHabitBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson

class RedactHabitFragment : Fragment() {
    private val dataModel: HabitsModel by activityViewModels()

    lateinit var habitNameEditText: EditText
    lateinit var descriptionEditText: EditText
    lateinit var prioritySpinner: Spinner
    lateinit var habitTypeRadioGroup: RadioGroup
    lateinit var frequencyEditText: EditText
    lateinit var periodicityEditText: EditText
    lateinit var saveButton: Button
    lateinit var binding:FragmentRedactHabitBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRedactHabitBinding.inflate(inflater)

        habitNameEditText = binding.editTextHabitName
        descriptionEditText = binding.editTextDescription
        prioritySpinner = binding.spinnerPriority
        habitTypeRadioGroup = binding.radioGroupHabitType
        frequencyEditText = binding.editTextFrequency
        periodicityEditText = binding.editTextPeriodicity
        saveButton = binding.buttonSave
        val position = arguments?.getInt("position")
        val type = arguments?.getString("type")
        val listNow = dataModel.habitList.value?.filter { it.type == type}
        val habit = position?.let { listNow?.get(it)  }
        if (habit != null && position != null) {
            habitNameEditText.setText(habit.name)
            descriptionEditText.setText(habit.info)

            saveButton.setOnClickListener {
                val checkedRadioButtonId = habitTypeRadioGroup.checkedRadioButtonId
                val selectedRadioButton =
                    habitTypeRadioGroup.findViewById<RadioButton>(checkedRadioButtonId)
                val res = selectedRadioButton.text.toString()
                val updatedHabit = Habit(
                    habitNameEditText.text.toString(),
                    descriptionEditText.text.toString(),
                    prioritySpinner.selectedItemId.toString(),
                    res,
                    frequencyEditText.text.toString(),
                    periodicityEditText.text.toString()
                )
                dataModel.habitList.value?.set(dataModel.habitList.value!!.indexOf(habit), updatedHabit)

                parentFragmentManager.popBackStack()
            }
        }
        else {
            saveButton.setOnClickListener {
                val checkedRadioButtonId = habitTypeRadioGroup.checkedRadioButtonId
                var res: String = ""
                if (checkedRadioButtonId != -1) {
                    val selectedRadioButton =
                        habitTypeRadioGroup.findViewById<RadioButton>(checkedRadioButtonId)
                    res = selectedRadioButton.text.toString()
                } else {
                    res = "Тип не выбран"
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
                val prefs: SharedPreferences =
                    requireActivity().getPreferences(Context.MODE_PRIVATE)
                val editor: SharedPreferences.Editor = prefs.edit()
                val gson = Gson()
                val json: String = gson.toJson(habit)
                editor.putString("habit", json)
                editor.apply()
                parentFragmentManager.popBackStack()
            }
        }

        return binding.root
    }
}
