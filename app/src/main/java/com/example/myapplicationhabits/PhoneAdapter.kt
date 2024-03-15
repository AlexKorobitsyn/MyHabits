//package com.example.myapplicationhabits
//
//import android.annotation.SuppressLint
//import android.app.Activity
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ArrayAdapter
//import android.widget.ImageView
//import android.widget.TextView
//
//class PhoneAdapter(private val context: Activity, private val arrayList: ArrayList<Habit>): ArrayAdapter<Habit>(context,
//    R.layout.item){
//    @SuppressLint("ViewHolder")
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//        val inflater: LayoutInflater = LayoutInflater.from(context)
//        val view: View = inflater.inflate(R.layout.item, null)
//        val name: TextView = view.findViewById(R.id.NameItem)
//        val info: TextView = view.findViewById(R.id.InfoItem)
//        val priority: TextView = view.findViewById(R.id.priorityItem)
//        val type: TextView = view.findViewById(R.id.typeItem)
//        val count: TextView = view.findViewById(R.id.countItem)
//        val freq: TextView = view.findViewById(R.id.freqItem)
//
//        name.text = arrayList[position].name
//        info.text = arrayList[position].info
//        priority.id = arrayList[position].priority!!
//        type.id = arrayList[position].type!!
//        count.text = arrayList[position].howMuch
//        freq.text = arrayList[position].inTime
//
//        return view
//    }
//}








//package com.example.myapplicationhabits
//
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import android.widget.Toast
//import androidx.recyclerview.widget.RecyclerView
//import com.example.myapplicationhabits.databinding.ItemBinding
//
//class MyAdapter(listArray:ArrayList<Habit>, context: Context):RecyclerView.Adapter<MyAdapter.ViewHolder>() {
//    var listArraR = listArray
//    var contextR = context
//    class ViewHolder(view:View): RecyclerView.ViewHolder(view) {
//        val binding =ItemBinding.bind(view)
//        val tvName = view.findViewById<TextView>(R.id.NameItem)
//        val tvInfo = view.findViewById<TextView>(R.id.InfoItem)
//        val tvPriority = view.findViewById<TextView>(R.id.priorityItem)
//        val tvType = view.findViewById<TextView>(R.id.typeItem)
//        val tvFreq = view.findViewById<TextView>(R.id.freqItem)
//        val tvCount = view.findViewById<TextView>(R.id.CountItem)
//        fun bind(listItem:Habit){
//
//            println(listItem)
//            tvName.text = listItem.name
//            tvInfo.text = listItem.info
//            tvPriority.text = listItem.priority
//            tvType.text = listItem.type
//            tvFreq.text = listItem.howMuch
//            tvCount.text = listItem.inTime
//            itemView.setOnClickListener(){
//                Toast.makeText(context, "Pressed: ${tvName.text}", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val inflater = LayoutInflater.from(contextR)
//        return ViewHolder(inflater.inflate(R.layout.item, parent, false))
//    }
//
//    override fun getItemCount(): Int {
//        return listArraR.size
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val listItem = listArraR.get(position)
//        holder.bind(listItem, contextR)
//
//    }
//}



