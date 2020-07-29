package ru.skillbranch.devintensive.extensions

import java.lang.Math.abs
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


fun Date.shortFormat(): String? {
    val pattern = if (this.isSameDay(Date())) "HH:mm" else "dd.MM.yy"
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}


fun Date.isSameDay(date: Date): Boolean {
    val day1 = this.time / DAY
    val day2 = date.time / DAY
    return day1 == day2
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
    val diff = date.time - this.time
    val back = if (diff<0) "" else " назад"
    val forward = if (diff<0) "через " else ""

    return when (abs(diff)) {
        in 0..SECOND -> "только что"
        in SECOND .. 45 * SECOND -> "${forward}несколько секунд$back"
        in 45 * SECOND .. 75 * SECOND -> "${forward}минуту$back"
        in 75 * SECOND .. 45 * MINUTE -> "$forward${TimeUnits.MINUTE.plural((abs(diff).div(MINUTE)).toInt())}$back"
        in 45 * MINUTE .. 75 * MINUTE -> "${forward}час$back"
        in 75 * MINUTE .. 22 * HOUR -> "$forward${TimeUnits.HOUR.plural((abs(diff).div(HOUR)).toInt())}$back"
        in 22 * HOUR .. 26 * HOUR -> "${forward}день назад"
        in 26 * HOUR .. 360 * DAY-> "$forward${TimeUnits.DAY.plural((abs(diff)/(DAY)).toInt())}$back"
        in 360 * DAY .. Long.MAX_VALUE-> if (forward.isBlank()) "более года назад" else "более чем через год"
        else -> ""
    }
}


enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY;

    fun plural(int:Int):String {
        when (this) {
            SECOND ->
                when (int % 10) {
                    1 -> return "$int секунду"
                    in 2..4 -> return "$int секунды"
                    0,in 5..9 -> return "$int секунд"
                }
            MINUTE ->
                when (int % 10) {
                    1 -> return "$int минуту"
                    in 2..4 -> return "$int минуты"
                    0,in 5..9 -> return "$int минут"
                }
            HOUR ->
                when (int % 10) {
                    1 -> return "$int час"
                    in 2..4 -> return "$int часа"
                    0,in 5..9 -> return "$int часов"
                }
            DAY ->
                when (int % 10) {
                    1 -> return "$int день"
                    in 2..4 -> return "$int дня"
                    0,in 5..9 -> return "$int дней"
                }
        }
        return ""
    }
}