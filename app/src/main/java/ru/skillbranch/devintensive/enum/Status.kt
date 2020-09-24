package ru.skillbranch.devintensive.enum

enum class Status(val color: Triple<Int, Int, Int>){
    NORMAL(Triple(255, 255, 255)),
    WARNING(Triple(255, 120, 0)),
    DANGER(Triple(255, 60, 60)),
    CRITICAL(Triple(255, 0, 0));

    fun nextStatus(): Status{
        return if (this.ordinal < values().lastIndex)
            values()[this.ordinal + 1]
        else values()[0]
    }
}