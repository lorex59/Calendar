package com.example.calendar

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

class CalendarUtils {

    companion object {

        var selectedDate: LocalDate? = null

        fun monthYearFromDate(date: LocalDate): String {
            val formatter = DateTimeFormatter.ofPattern("MMMM yyyy")
            return date.format(formatter)
        }

        fun daysInMonthArray(date: LocalDate): List<LocalDate?> {
            val daysInMonthArray = mutableListOf<LocalDate?>()
            val yearMonth = YearMonth.from(date)

            val daysInMonth = yearMonth.lengthOfMonth()
            val firstOfMonth = CalendarUtils.selectedDate?.withDayOfMonth(1)
            val dayOfWeek = firstOfMonth?.dayOfWeek?.value!!

            for (i in 1..42) {
                if (i <= dayOfWeek || i > daysInMonth + dayOfWeek) {
                    daysInMonthArray.add(null)
                } else {
                    daysInMonthArray.add(
                        LocalDate.of(
                            selectedDate?.year!!,
                            selectedDate?.month,
                            i - dayOfWeek
                        )
                    )
                }
            }

            return daysInMonthArray
        }

        fun daysInWeekArray(date: LocalDate): List<LocalDate> {
            val days = mutableListOf<LocalDate>()

            val current: LocalDate? = sundayForDate(date)

            if (current != null) {
                var currentNotNull: LocalDate = current

                val endDate = currentNotNull.plusWeeks(1)

                while (currentNotNull.isBefore(endDate)) {
                    days.add(currentNotNull)
                    currentNotNull = currentNotNull.plusDays(1)
                }
            }
            return days
        }

        private fun sundayForDate(_current: LocalDate): LocalDate? {
            var current = _current
            val oneWeekAgo = current.minusWeeks(1)

            while (current.isAfter(oneWeekAgo)) {
                if (current.dayOfWeek == DayOfWeek.SUNDAY) return current

                current = current.minusDays(1)
            }
            return  null
        }

    }


}