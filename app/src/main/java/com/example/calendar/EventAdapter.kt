package com.example.calendar

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.calendar.databinding.EventCellBinding
import java.time.LocalDate
import java.time.LocalTime

class EventAdapter(
    private val events: List<Event>
) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    inner class EventViewHolder(
        view: View,
        private val events: List<Event>
    ) : RecyclerView.ViewHolder(view) {
        val binding = EventCellBinding.bind(view)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = inflater.inflate(R.layout.event_cell, parent, false)

        return EventViewHolder(binding, events)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val time: LocalTime =  events[position].time
        val titleEvent: String = events[position].name

        with(holder.binding) {
            eventName.text = titleEvent
            eventTime.text = CalendarUtils.formattedTime(time)
        }
    }

    override fun getItemCount(): Int {
        return events.size
    }

}