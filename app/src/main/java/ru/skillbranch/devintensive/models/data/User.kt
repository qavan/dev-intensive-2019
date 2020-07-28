package ru.skillbranch.devintensive.models.data

import ru.skillbranch.devintensive.extensions.humanizeDiff
import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class User (
    val id : String,
    var firstName : String?,
    var lastName : String?,
    var avatar : String?,
    var rating : Int = 0,
    var respect : Int = 0,
    val lastVisit : Date? = null,
    val isOnline : Boolean = false
) {
    fun toUserItem(): UserItem {
        val lastActivity = when{
            lastVisit == null -> "Ещё ни разу не заходил"
            isOnline -> "online"
            else -> "Последний раз был ${lastVisit.humanizeDiff()}"
        }
        return UserItem(
            id,
            "${firstName.orEmpty()} ${lastName.orEmpty()}",
            Utils.toInitials(firstName,lastName),
            avatar,
            lastActivity,
            false,
            isOnline
        )
    }

    constructor(id:String,firstName: String?,lastName: String?) : this(id=id,firstName = firstName,lastName = lastName,avatar = null)
    constructor(id:String?) : this (id.toString(), "John", "Doe $id")

    init {
//        print("It's alive!!!\n" +
//                "${if(lastName==="Doe") "His name id $firstName $lastName" else "And his name is $firstName $lastName!!!"}\n")
    }

    class Builder {
        lateinit var id : String
        var firstName : String? = null
        var lastName : String? = null
        var avatar : String? = null
        var rating : Int = 0
        var respect : Int = 0
        var lastVisit : Date? = Date()
        var isOnline : Boolean = false

        fun build(): User {
            return User(
                id = id,
                firstName = firstName,
                lastName = lastName,
                avatar = avatar,
                rating = rating,
                respect = respect,
                lastVisit = lastVisit,
                isOnline = isOnline
            )
        }
        fun id(id: String): Builder {
            this.id=id
            return this
        }
        fun firstName(firstName: String): Builder {
            this.firstName=firstName
            return this
        }
        fun lastName(lastName: String): Builder {
            this.lastName=lastName
            return this
        }
        fun avatar(avatar: String): Builder {
            this.avatar=avatar
            return this
        }
        fun rating(rating: Int): Builder {
            this.rating=rating
            return this
        }
        fun respect(respect: Int): Builder {
            this.respect=respect
            return this
        }
        fun lastVisit(lastVisit: Date): Builder {
            this.lastVisit=lastVisit
            return this
        }
        fun isOnline(isOnline: Boolean): Builder {
            this.isOnline=isOnline
            return this
        }
    }

    companion object Factory {
        private var lastId : Int = -1
        fun makeUser(fullName: String?) : User {
            lastId++
            val (firstName,lastName) = Utils.parseFullName(fullName)
            return User(
                id = "$lastId",
                firstName = firstName,
                lastName = lastName
            )
        }
    }
}