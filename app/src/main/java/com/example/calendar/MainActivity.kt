package com.example.calendar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.calendar.databinding.ActivityMainBinding
import java.time.LocalDate

class MainActivity : AppCompatActivity(), CalendarAdapter.OnItemListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        CalendarUtils.selectedDate = LocalDate.now()

        initWidgets()
        setMonthView()
    }

    private fun setMonthView() {
        binding.monthYearTV.text = CalendarUtils.monthYearFromDate(CalendarUtils.selectedDate!!)

        val daysOfMonth = CalendarUtils.daysInMonthArray(CalendarUtils.selectedDate!!)

        val calendarAdapter = CalendarAdapter(daysOfMonth, this)
        binding.calendarRecyclerView.layoutManager = GridLayoutManager(this, 7)
        binding.calendarRecyclerView.adapter = calendarAdapter
    }

    fun previousMonthAction(view: View) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate?.minusMonths(1)
        setMonthView()
    }

    fun nextMonthAction(view: View) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate?.plusMonths(1)
        setMonthView()
    }

    override fun onItemClick(position: Int, date: LocalDate?) {
        if (date != null) {
            CalendarUtils.selectedDate = date
            setMonthView()
            Toast.makeText(this, date.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun initWidgets() {
    }

    fun weekly(viw: View) {
        startActivity(Intent(this, WeeklyActivity::class.java))
    }
}