package com.example.calendar

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.calendar.CalendarAdapter.*
import com.example.calendar.databinding.CalendarCellBinding
import java.time.LocalDate

class CalendarAdapter(
    private val days: List<LocalDate?>,
    private val onItemListener: OnItemListener
) : RecyclerView.Adapter<CalendarViewHolder>() {


    inner class CalendarViewHolder(
        view: View,
        private val onItemListener: OnItemListener,
        private val days: List<LocalDate?>
    ) : RecyclerView.ViewHolder(view),
        View.OnClickListener {

        val binding = CalendarCellBinding.bind(view)

        init {
            binding.root.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            onItemListener.onItemClick(adapterPosition, days[adapterPosition])
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.calendar_cell, parent, false)
        val layoutParams = view.layoutParams

        if (days.size > 15) { //month view
            layoutParams.height = (parent.height * 0.166666666).toInt()
        } else {
            layoutParams.height = parent.height
        }


        return CalendarViewHolder(view, onItemListener, days)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        val date: LocalDate? = days[position]

        if (date == null) {
            holder.binding.cellDayText.text = ""
        } else {
            holder.binding.cellDayText.text = date.dayOfMonth.toString()
            if (date == CalendarUtils.selectedDate) {
                holder.binding.parentView.setBackgroundColor(Color.LTGRAY)
            }
        }
    }

    override fun getItemCount(): Int {
        return days.size
    }

    interface OnItemListener {

        fun onItemClick(position: Int, date: LocalDate?)

    }

}