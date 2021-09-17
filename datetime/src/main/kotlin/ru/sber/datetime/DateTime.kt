package ru.sber.datetime

import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.*

// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> =
    ZoneId.getAvailableZoneIds().asSequence()
            .filter {
                ZonedDateTime.now(ZoneId.of(it)).minute !=
                        ZonedDateTime.now(ZoneId.of("UTC")).minute
            }
            .toSet()


// 2.
fun getLastInMonthDayWeekList(year: Int): List<String> =
        Month.values().asSequence()
                .map { month ->
                    LocalDateTime
                            .of(year, month, 1, 1, 1)
                            .with(TemporalAdjusters.lastDayOfMonth())
                            .dayOfWeek
                            .name }
                .toList()

// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int =
        Month.values().asSequence()
                .filter { month ->
                    LocalDateTime
                            .of(year, month, 13, 1, 1)
                            .dayOfWeek == DayOfWeek.FRIDAY }
                .count()

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String =
        dateTime.format(DateTimeFormatter
                .ofPattern("dd MMM yyyy, HH:mm")
                .withLocale(Locale.US))



