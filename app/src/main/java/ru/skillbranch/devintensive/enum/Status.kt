package ru.skillbranch.devintensive.enum

import ru.skillbranch.devintensive.R

enum class Status(val color: Int){
    NORMAL(R.color.color_white),
    WARNING(R.color.color_yellow),
    DANGER(R.color.color_orange),
    CRITICAL(R.color.color_red);

    fun nextStatus(): Status{
        return if (this.ordinal < values().lastIndex)
            values()[this.ordinal + 1]
        else values()[0]
    }
}