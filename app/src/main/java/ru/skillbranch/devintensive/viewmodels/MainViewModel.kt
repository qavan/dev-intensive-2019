package ru.skillbranch.devintensive.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import ru.skillbranch.devintensive.extensions.mutableLiveData
import ru.skillbranch.devintensive.models.BaseMessage
import ru.skillbranch.devintensive.models.data.Chat
import ru.skillbranch.devintensive.models.data.ChatItem
import ru.skillbranch.devintensive.repositories.ChatRepository

class MainViewModel :  ViewModel() {
    private val query = mutableLiveData("")
    private val chatRepository = ChatRepository

    private val chats: LiveData<List<ChatItem>>  = Transformations.map(chatRepository.loadChats()) {chats ->
        val archivedCandidate = listOf(chats.filter{it.isArchived}.sortedBy { it.lastMessageDate() })
        val archivedChat = if (archivedCandidate[0].isNotEmpty())
                            {
                                val messages = mutableListOf<BaseMessage>()
                                archivedCandidate[0].forEach { messages +=it.messages }
                                archivedCandidate[0][0].copy(id="-1",messages = messages) //it.messages.sumBy{it.isReaded == false}
                            }
                            else
                                Chat("","")
        val chatS = if (archivedChat.id != "")
                        (listOf(archivedChat) + chats.filter{!it.isArchived})
                    else
                        chats.filter{!it.isArchived}
        Log.d("M_MainViewModel","${chatS.size}")
        return@map chatS.map{it.toChatItem()}.sortedBy { it.id.toInt() }
    }

    fun getChatData() : LiveData<List<ChatItem>> {
        val result = MediatorLiveData<List<ChatItem>>()

        val filterF = {
            val queryStr = query.value!!
            val chats = chats.value!!

            result.value = if (queryStr.isEmpty()) chats else chats.filter { it.title.contains(queryStr,true) }
        }

        result.addSource(chats){filterF.invoke()}
        result.addSource(query){filterF.invoke()}

        return result
    }

    fun addToArchive(chatId: String) {
        val chat = chatRepository.find(chatId)
        chat ?: return
        Log.d("M_MainViewModel","addToArchive $chatId")
        chatRepository.update(chat.copy(isArchived = true))
    }

    fun restoreFromArchive(chatId: String) {
        val chat = chatRepository.find(chatId)
        chat ?: return
        Log.d("M_MainViewModel","restoreFromArchive $chatId")
        chatRepository.update(chat.copy(isArchived = false))
    }

    fun handleSearchQuery(text: String) {
        query.value = text
    }
}