package ru.skillbranch.devintensive.extensions

import java.lang.IllegalStateException
import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern:String="HH:mm:ss dd.MM.yy"):String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value:Int, units: TimeUnits = TimeUnits.SECOND): Date{
    var time = this.time

    time += when(units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date= Date()): String {
    return ""
}


enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}

fun TimeUnits.plural(int: Int):String {
    when (this) {
        TimeUnits.SECOND ->
            when (int % 10) {
                1 -> return "$int секунду"
                in 2..4 -> return "$int секунды"
                0,in 5..9 -> return "$int секунд"
            }
        TimeUnits.MINUTE ->
            when (int % 10) {
                1 -> return "$int минуту"
                in 2..4 -> return "$int минуты"
                0,in 5..9 -> return "$int минут"
            }
        TimeUnits.HOUR ->
            when (int % 10) {
                1 -> return "$int час"
                in 2..4 -> return "$int часа"
                0,in 5..9 -> return "$int часов"
            }
        TimeUnits.DAY ->
            when (int % 10) {
                1 -> return "$int день"
                in 2..4 -> return "$int дня"
                0,in 5..9 -> return "$int дней"
        }
    }
    return ""
}