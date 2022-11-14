package com.example.calendar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.calendar.CalendarUtils.Companion.selectedDate
import com.example.calendar.databinding.ActivityDailyBinding
import java.time.LocalTime
import java.time.format.TextStyle
import java.util.*
import kotlin.collections.ArrayList

class DailyActivity : AppCompatActivity() {

    lateinit var binding: ActivityDailyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDailyBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onResume() {
        super.onResume()
        setDailyView()
        setHourAdapter()
    }

    private fun setHourAdapter() {
        val hourAdapter = HourAdapter(hourEventList())
        binding.hourRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.hourRecyclerView.adapter = hourAdapter
    }

    private fun hourEventList(): ArrayList<HourEvent> {

        var tempList: ArrayList<HourEvent> = ArrayList()
        for (hour in 0 until 24) {
            val localTime = LocalTime.of(hour,0)
            var events = selectedDate?.let { Event.eventsForDateAndTime(it, localTime) }
            var hourEvent = events?.let { HourEvent(localTime, it) }
            if (hourEvent != null) {
                tempList.add(hourEvent)
            }
        }
        return tempList
    }


    private fun setDailyView() {
        binding.monthYearTV.text = selectedDate?.let { CalendarUtils.monthDayFromDate(it) }
        var dayOfWeek = selectedDate?.dayOfWeek?.getDisplayName(TextStyle.FULL, Locale.getDefault())
        binding.dayOfWeekTV.text = dayOfWeek
    }

    fun prevDayAction(view: View) {
        selectedDate = selectedDate?.minusDays(1)
        setDailyView()
    }

    fun nextDayAction(view: View) {
        selectedDate = selectedDate?.plusDays(1)
        setDailyView()
    }

    fun newEventAction(view: View) {
        startActivity(Intent(this, EventEditActivity::class.java))
    }
}