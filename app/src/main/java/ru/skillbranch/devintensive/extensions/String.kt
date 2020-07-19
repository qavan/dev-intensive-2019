package ru.skillbranch.devintensive.extensions

fun String.truncate(int:Int=16):String {
    var candidate = substring(0,int)
    var isChanged = false
    while (candidate[candidate.length-1].toString() == " ") {
        isChanged = true
        candidate = candidate.substring(0,candidate.length-1)
    }
    return if (!isChanged) "$candidate..."
    else candidate
}

fun String.stripHtml():String {
    return Regex("( ){1,}").replace(Regex("<[^>]*>").replace(this,"")," ")
}