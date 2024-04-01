package com.example.myapplicationhabits

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class Habit(var name: String?, var info: String?, var priority: String?, var type:String?,
                 var howMuch:String?, var inTime:String?): Parcelable, Serializable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        val arr: Array<String?> = arrayOf(name, info, priority, type, howMuch, inTime)
        parcel.writeStringArray(arr)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Habit> {
        override fun createFromParcel(parcel: Parcel): Habit {
            return HabitInfo(parcel)
        }

        private fun HabitInfo(parcel: Parcel): Habit {
            val arr1 = arrayOfNulls<String>(6)
            parcel.readStringArray(arr1)
            return Habit(arr1[0], arr1[1], arr1[2], arr1[3],arr1[4], arr1[5])
        }

        override fun newArray(size: Int): Array<Habit?> {
            return arrayOfNulls(size)
        }
    }
}
