package ru.skillbranch.devintensive.models.data

import ru.skillbranch.devintensive.extensions.shortFormat
import ru.skillbranch.devintensive.models.BaseMessage
import ru.skillbranch.devintensive.models.ImageMessage
import ru.skillbranch.devintensive.models.TextMessage
import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class Chat(
    val id:String,
    val title:String,
    val members: List<User> = listOf(),
    var messages: MutableList<BaseMessage> = mutableListOf(),
    var isArchived:Boolean = false
) {

    fun unreadableMessageCount():Int {
        return if (messages.isNullOrEmpty()) {
            0
        } else {
            messages.filter { !it.isReaded }.count()
        }
    }

    fun lastMessageDate(): Date {
        return if (messages.isNullOrEmpty()) Date()
        else {
            messages.last().date
        }
    }

    fun lastMessageShort():Pair<String, String> {
        return if (messages.isNullOrEmpty()) "Сообщений еще нет" to "@John_Doe"
        else {
            when {
                messages.last() is TextMessage -> (messages.last() as TextMessage).text.toString() to "${messages.last().from.firstName}"
                messages.last() is ImageMessage -> "отправил фото" to "${messages.last().from.firstName} ${messages.last().from.firstName}"
                else -> (messages.last() as TextMessage).text.toString() to messages.last().from.firstName.toString()
            }
        }
    }

    private fun isSingle():Boolean = members.size == 1

    fun toChatItem(): ChatItem {
        return  if (isSingle()) {
            val user = members.first()
            ChatItem(
                id,
                user.avatar,
                Utils.toInitials(user.firstName,user.lastName) ?: "??",
                "${user.firstName?:""} ${user.lastName?:""}",
                lastMessageShort().first,
                unreadableMessageCount(),
                lastMessageDate().shortFormat(),
                user.isOnline
            )
        }
        else {
            ChatItem(
                id,
                null,
                "",
                title,
                lastMessageShort().first,
                unreadableMessageCount(),
                lastMessageDate().shortFormat(),
                false,
                ChatType.GROUP,
                lastMessageShort().second
            )
        }
    }

enum class ChatType{
    SINGLE,
    GROUP,
    ARCHIVE
}
}
