package com.example.myapplicationhabits

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationhabits.databinding.ItemBinding

class MyAdapter(listArray:ArrayList<Habit>, context: Context):RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    var listArraR = listArray
    var contextR = context
    class ViewHolder(view:View): RecyclerView.ViewHolder(view) {
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
            itemView.setOnClickListener(){
                val intent = Intent(itemView.context, AddHabitActivity::class.java)
                println("position = $pos")
                intent.putExtra("position", pos)
                startActivity(itemView.context, intent,bundle )
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return listArraR.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listArraR[position], position)

    }
}