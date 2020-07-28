package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.models.data.User
import ru.skillbranch.devintensive.utils.Utils

fun User.toUserView() : UserView {

    val nickName = Utils.transliteration("$firstName $lastName")
    val initials = Utils.toInitials(firstName,lastName)
    val status = if (lastVisit == null) "Ещё ни разу не был в сети" else if (isOnline) "online" else "Последний раз был в сети ${lastVisit!!.humanizeDiff()}"

    return UserView(
        id,
        fullName = "$firstName $lastName",
        avatar = "$avatar",
        nickName = nickName,
        initials = initials,
        status = status
    )
}