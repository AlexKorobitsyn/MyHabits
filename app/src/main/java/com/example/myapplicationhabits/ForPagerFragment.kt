//package com.example.myapplicationhabits
//
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import com.example.myapplicationhabits.databinding.ActivityMainBinding
//import com.example.myapplicationhabits.databinding.FragmentForPagerBinding
//
//
//class ForPagerFragment : Fragment() {
//    lateinit var binding: FragmentForPagerBinding
//    private val fragList = listOf(
//        FirstFragMain.newInstance(),
//        EmotionalFragMain.newInstance(),
//        PhysicalFragMain.newInstance()
//    )
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentForPagerBinding.inflate(inflater)
//        val adapter = VpAdapter(this, fragList)
//        binding.fragmentPager.adapter = adapter
//        // Inflate the layout for this fragment
//        return binding.root
//    }
//
//    companion object {
//        @JvmStatic
//        fun newInstance() =
//            ForPagerFragment()
//    }
//}