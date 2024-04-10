package com.example.myapplicationhabits

import RedactHabitFragment
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationhabits.databinding.ItemBinding

class MyAdapter(listArray: List<Habit>, context: Context, type: String):RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    var listArraR = listArray
    var contextR = context
    var type1 = type

    class ViewHolder(view:View, private val context: Context, type1: String): RecyclerView.ViewHolder(view) {
        val binding =ItemBinding.bind(view)
        fun bind(listItem:Habit, pos: Int, type1: String){
            binding.NameItem.text = listItem.name.toString()
            binding.InfoItem.text = listItem.info.toString()
            binding.priorityItem.text = listItem.priority
            binding.typeItem.text = listItem.type
            binding.freqItem.text = listItem.howMuch
            binding.CountItem.text = listItem.inTime
            val bundle:Bundle = Bundle()
            bundle.putInt("position", pos)
            itemView.setOnClickListener {
                val redactHabitFragment = RedactHabitFragment()
                val bundle = Bundle()
                bundle.putParcelable("habit", listItem)
                bundle.putInt("position", pos)
                bundle.putString("type", type1)
                redactHabitFragment.arguments = bundle
                (context as FragmentActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, redactHabitFragment)
                    .addToBackStack(null)
                    .commit()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(inflater, contextR, type1)
    }

    override fun getItemCount(): Int {
        return listArraR.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(listArraR[position], position, type1)

    }
}