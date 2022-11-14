package com.example.calendar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.calendar.databinding.HourCellBinding
import java.time.LocalTime

class HourAdapter(
    private val hourEvents: ArrayList<HourEvent>
) : RecyclerView.Adapter<HourAdapter.HourViewHolder>() {

    inner class HourViewHolder(
        view: View,
        private val events: List<HourEvent>
    ) : RecyclerView.ViewHolder(view) {
        val binding = HourCellBinding.bind(view)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = inflater.inflate(R.layout.hour_cell, parent, false)

        return HourViewHolder(binding, hourEvents)
    }

    override fun onBindViewHolder(holder: HourViewHolder, position: Int) {
        var event = hourEvents[position]

        setHour(holder.binding, event.time)
        setEvents(holder.binding, event.events)
    }

    private fun setEvents(binding: HourCellBinding, events: ArrayList<Event>) {
        if (events.isEmpty()) {
            hideEvent(binding.event1)
            hideEvent(binding.event2)
            hideEvent(binding.event3)
        } else if (events.size == 1) {
            setEvent(binding.event1, events[0])
            hideEvent(binding.event2)
            hideEvent(binding.event3)
        }
        else if (events.size == 2) {
            setEvent(binding.event1, events[0])
            setEvent(binding.event2, events[1])
            hideEvent(binding.event3)
        } else if (events.size == 3){
            setEvent(binding.event1, events[0])
            setEvent(binding.event2, events[1])
            setEvent(binding.event3, events[2])
        } else {
            setEvent(binding.event1, events[0])
            setEvent(binding.event2, events[1])
            binding.event3.visibility = View.VISIBLE
            var temp = (events.size - 2).toString()
            binding.event3.text = "More Events $temp"
        }
    }

    private fun setEvent(event1: TextView, event: Event) {
        event1.text = event.name
        event1.visibility = View.VISIBLE
    }

    private fun hideEvent(event1: TextView) {
        event1.visibility = View.INVISIBLE
    }


    private fun setHour(binding: HourCellBinding, time: LocalTime) {
        binding.timeCell.text = CalendarUtils.formattedShortTime(time)
    }


    override fun getItemCount(): Int {
        return hourEvents.size
    }

}