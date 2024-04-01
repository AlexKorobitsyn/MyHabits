package com.example.myapplicationhabits

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class HabitsModel: ViewModel() {
    val habitList : MutableLiveData<ArrayList<Habit>> by lazy {
        MutableLiveData<ArrayList<Habit>>()
    }
    val habits: MutableLiveData<Habit> by lazy{
        MutableLiveData<Habit>()
    }
    val counter:MutableLiveData<Int> by lazy {
        MutableLiveData()
    }
    fun getHabitsList() = habitList
    fun setHabits(hab:Habit) {
        habits.value = hab
    }
//    fun setHabitsList() {
//        habitList.value = ArrayList()
//        counter.value = 0
//    }

    fun addNewHabit(hab:Habit){
        if (habitList.value == null){
            habitList.value = ArrayList()
        }
        if (counter.value == null){
            counter.value = 0
        }
        habitList.value?.add(hab)
        counter.value= counter.value?.plus(1)
        println("VIEWMODEL  ${counter.value}  ${habitList.value}")
    }
}