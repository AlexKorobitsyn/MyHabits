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
        val habit = arguments?.getParcelable<Habit>("habit")
        val position = arguments?.getInt("position")
        if (habit != null && position != null) {
            // Заполняем поля редактирования данными из Habit
            habitNameEditText.setText(habit.name)
            descriptionEditText.setText(habit.info)


            saveButton.setOnClickListener {
                // Обновляем Habit в yourArrayList по полученной позиции
                val updatedHabit = Habit(
                    habitNameEditText.text.toString(),
                    descriptionEditText.text.toString(),
                    prioritySpinner.selectedItemId.toString(),
                    "Тип не выбран",
                    frequencyEditText.text.toString(),
                    periodicityEditText.text.toString()
                    // Добавьте остальные поля Habit здесь...
                )
                // Предполагается, что у вас есть доступ к yourArrayList в этом контексте
                dataModel.habitList.value?.set(position, updatedHabit)

                // Обновляем данные в RecyclerView
                // Предполагается, что у вас есть метод для обновления RecyclerView

                // Возвращаемся к FirstFragMain
                parentFragmentManager.popBackStack()
            }
        }
        else {
            saveButton.setOnClickListener {
                val checkedRadioButtonId = habitTypeRadioGroup.checkedRadioButtonId
                println("RedactHabitOnclick $checkedRadioButtonId")
                var res: String = ""
                if (checkedRadioButtonId != -1) {
                    // Получаем выбранную радио-кнопку
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
                println("REDACTCOUNTERHABITS : ${dataModel.counter.value}")
                val prefs: SharedPreferences =
                    requireActivity().getPreferences(Context.MODE_PRIVATE)
                val editor: SharedPreferences.Editor = prefs.edit()
                val gson = Gson()
                val json: String = gson.toJson(habit)
                editor.putString("habit", json)
                editor.apply()
                parentFragmentManager.popBackStack()
                // Закрываем активность после сохранения
            }
        }

        return binding.root
    }
}
