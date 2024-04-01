package com.example.myapplicationhabits

import RedactHabitFragment
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationhabits.databinding.ItemBinding

class MyAdapter(listArray:ArrayList<Habit>, context: Context):RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    var listArraR = listArray
    var contextR = context
    class ViewHolder(view:View, private val context: Context): RecyclerView.ViewHolder(view) {
        val binding =ItemBinding.bind(view)
        fun bind(listItem:Habit, pos: Int){
            binding.NameItem.text = listItem.name.toString()
            binding.InfoItem.text = listItem.info.toString()
            binding.priorityItem.text = listItem.priority
            binding.typeItem.text = listItem.type
            binding.freqItem.text = listItem.howMuch
            binding.CountItem.text = listItem.inTime
            val bundle:Bundle = Bundle()
            bundle.putInt("position", pos)
            itemView.setOnClickListener {
                // Создаем новый экземпляр RedactHabitFragment
                val redactHabitFragment = RedactHabitFragment()
                // Создаем Bundle для передачи данных
                val bundle = Bundle()
                bundle.putParcelable("habit", listItem) // Предполагается, что Habit реализует Parcelable
                bundle.putInt("position", pos)
                redactHabitFragment.arguments = bundle
                // Заменяем текущий фрагмент на RedactHabitFragment
                (context as FragmentActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, redactHabitFragment)
                    .addToBackStack(null)
                    .commit()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(inflater, contextR)
    }

    override fun getItemCount(): Int {
        return listArraR.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listArraR[position], position)

    }
}