package com.example.calendar

import java.time.LocalDate
import java.time.LocalTime

data class Event(
     var name: String,
     var date: LocalDate,
     val time: LocalTime
) {

    companion object {
        var eventsList = ArrayList<Event>()

        fun eventsForDate(date: LocalDate): ArrayList<Event> {
            var events = ArrayList<Event>()

            for (event in eventsList) {
                if(event.date.equals(date)){
                    events.add(event)
                }
            }

            return events
        }

        fun eventsForDateAndTime(date: LocalDate, time: LocalTime): ArrayList<Event> {
            var events = ArrayList<Event>()

            for (event in eventsList) {
                var eventHour = event.time.hour
                var cellHour = time.hour
                if(event.date.equals(date) && eventHour == cellHour){
                    events.add(event)
                }
            }

            return events
        }
    }
}