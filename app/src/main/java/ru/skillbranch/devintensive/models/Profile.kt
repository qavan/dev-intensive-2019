package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.lang.StringBuilder

data class Profile(
    val firstName: String,
    val lastName: String,
    val about: String,
    val repository: String,
    val rating: Int = 0,
    val respect: Int = 0
) {
    val nickName: String = if (firstName !="" && lastName !="") Utils.transliteration(StringBuilder().append(firstName).append("_").append(lastName).toString(),"_") else "John Doe"
    val rank: String = "Junior Android Developer"
    fun toMap():Map<String,Any> = mapOf(
        "nickName" to nickName,
        "rank" to rank,
        "firstName" to firstName,
        "lastName" to lastName,
        "about" to about,
        "repository" to repository,
        "rating" to rating,
        "respect" to respect
    )
}