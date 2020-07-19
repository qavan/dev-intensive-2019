package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.extensions.humanizeDiff
import java.util.*

class TextMessage(
    id:String,
    from:User?,
    chat:Chat,
    isComing:Boolean = false,
    date: Date = Date(),
    var text: String
) : BaseMessage(id,from,chat,isComing,date) {
    override fun formatMessage(): String  = "id:$id ${from?.firstName}" +
            " ${if (isComing) "получил" else "отправил"} сообщение \"$text\" ${date.humanizeDiff()}"
}