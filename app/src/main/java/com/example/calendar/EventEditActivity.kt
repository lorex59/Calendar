package com.example.calendar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.calendar.databinding.ActivityEventEditBinding
import java.time.LocalTime

class EventEditActivity : AppCompatActivity() {

    lateinit var binding: ActivityEventEditBinding
    lateinit var time: LocalTime


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        time = LocalTime.now()
        with(binding){
            eventDateTv.text = "Date: ${CalendarUtils.selectedDate?.let { //TODO() возможно херню сделал!!!
                CalendarUtils.formattedDate(
                    it
                )
            }}"
            eventTimeTV.text = "Time: ${CalendarUtils.formattedTime(time)}"
        }

    }

    fun saveEvent(view: View) {
        var eventname = binding.eventTimeTV.text.toString()
        var newEvent = CalendarUtils.selectedDate?.let { Event(eventname, it, time) }
        if (newEvent != null) {
            Event.eventsList.add(newEvent)
        }
    }
}