package com.example.calendar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.calendar.databinding.ActivityWeeklyBinding
import java.time.LocalDate

class WeeklyActivity : AppCompatActivity(), CalendarAdapter.OnItemListener {

    private lateinit var binding: ActivityWeeklyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeeklyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initWidgets()
        setWeekView()
    }

    fun newEventAction(view: View) {
        startActivity(Intent(this, EventEditActivity::class.java))
    }

    override fun onResume() {
        super.onResume()
        setEventAdapter()
    }

    fun setEventAdapter() {
        val dailyEvents = CalendarUtils.selectedDate?.let { Event.eventsForDate(it) }
        val adapter = dailyEvents?.let { EventAdapter(it) }
        binding.recyclerViewEvent.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewEvent.adapter = adapter
    }

    private fun setWeekView() {
        binding.monthYearTV.text = CalendarUtils.monthYearFromDate(CalendarUtils.selectedDate!!)

        val days: List<LocalDate> = CalendarUtils.daysInWeekArray(CalendarUtils.selectedDate!!)
        val calendarAdapter = CalendarAdapter(days, this)

        binding.calendarRecyclerView.layoutManager = GridLayoutManager(this, 7)
        binding.calendarRecyclerView.adapter = calendarAdapter


    }

    fun previousWeekAction(view: View) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate?.minusWeeks(1)
        setWeekView()
    }

    fun nextWeekAction(view: View) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate?.plusWeeks(1)
        setWeekView()
    }

    private fun initWidgets() {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate?.plusWeeks(1)
        setEventAdapter()
    }

    override fun onItemClick(position: Int, date: LocalDate?) {
        CalendarUtils.selectedDate = date
        setWeekView()
    }

    fun dailyActivity(view: View) {
        startActivity(Intent(this, DailyActivity::class.java))
    }

    fun prevDayAction(view: View) {}
    fun nextDayAction(view: View) {}
}