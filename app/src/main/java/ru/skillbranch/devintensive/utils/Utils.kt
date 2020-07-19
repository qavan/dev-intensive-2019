package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName:String?):Pair<String?,String?> {
        if (fullName==null || fullName.isNullOrBlank()) return "null" to "null"
        val parts : List<String>? = fullName.removePrefix(" ").split(" ")
        if (parts != null) {
            return when (parts.size) {
                2 -> {
                    val firstName = parts[0]
                    val lastName = parts[1]
                    firstName to lastName
                }
                1 -> {
                    val firstName = parts[0]
                    firstName to "null"
                }
                else ->
                    return "null" to "null"
            }
        }
        else return "null" to "null"
    }

    fun transliteration(payload: String, divider: String=" "): String {
        var result = ""
        payload.forEach{
            when(it) {
                'а' -> result += 'a'
                'б' -> result += 'b'
                'в' -> result += 'v'
                'г' -> result += 'g'
                'д' -> result += 'd'
                'е' -> result += 'e'
                'ё' -> result += 'e'
                'ж' -> result += "zh"
                'з' -> result += 'z'
                'и' -> result += 'i'
                'й' -> result += 'i'
                'к' -> result += 'k'
                'л' -> result += 'l'
                'м' -> result += 'm'
                'н' -> result += 'n'
                'о' -> result += 'o'
                'п' -> result += 'p'
                'р' -> result += 'r'
                'с' -> result += 's'
                'т' -> result += 't'
                'у' -> result += 'u'
                'ф' -> result += 'f'
                'х' -> result += 'h'
                'ц' -> result += 'c'
                'ч' -> result += "ch"
                'ш' -> result += "sh"
                'щ' -> result += "sh"
                'ъ' -> result += ""
                'ы' -> result += 'i'
                'ь' -> result += ""
                'э' -> result += 'e'
                'ю' -> result += "yu"
                'я' -> result += "ya"
                'а'.toUpperCase() -> result += 'a'.toUpperCase()
                'б'.toUpperCase() -> result += 'b'.toUpperCase()
                'в'.toUpperCase() -> result += 'v'.toUpperCase()
                'г'.toUpperCase() -> result += 'g'.toUpperCase()
                'д'.toUpperCase() -> result += 'd'.toUpperCase()
                'е'.toUpperCase() -> result += 'e'.toUpperCase()
                'ё'.toUpperCase() -> result += 'e'.toUpperCase()
                'ж'.toUpperCase() -> result += "Zh"
                'з'.toUpperCase() -> result += 'z'.toUpperCase()
                'и'.toUpperCase() -> result += 'i'.toUpperCase()
                'й'.toUpperCase() -> result += 'i'.toUpperCase()
                'к'.toUpperCase() -> result += 'k'.toUpperCase()
                'л'.toUpperCase() -> result += 'l'.toUpperCase()
                'м'.toUpperCase() -> result += 'm'.toUpperCase()
                'н'.toUpperCase() -> result += 'n'.toUpperCase()
                'о'.toUpperCase() -> result += 'o'.toUpperCase()
                'п'.toUpperCase() -> result += 'p'.toUpperCase()
                'р'.toUpperCase() -> result += 'r'.toUpperCase()
                'с'.toUpperCase() -> result += 's'.toUpperCase()
                'т'.toUpperCase() -> result += 't'.toUpperCase()
                'у'.toUpperCase() -> result += 'u'.toUpperCase()
                'ф'.toUpperCase() -> result += 'f'.toUpperCase()
                'х'.toUpperCase() -> result += 'h'.toUpperCase()
                'ц'.toUpperCase() -> result += 'c'.toUpperCase()
                'ч'.toUpperCase() -> result += "Ch"
                'ш'.toUpperCase() -> result += "Sh"
                'щ'.toUpperCase() -> result += "Sh"
                'ъ'.toUpperCase() -> result += ""
                'ы'.toUpperCase() -> result += 'i'.toUpperCase()
                'ь'.toUpperCase() -> result += ""
                'э'.toUpperCase() -> result += 'e'.toUpperCase()
                'ю'.toUpperCase() -> result += "Yu"
                'я'.toUpperCase() -> result += "Ya"
                ' ' -> result += divider
                else -> result += it
            }
        }
        return result
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        return if (firstName != null && lastName != null && !firstName.isNullOrBlank() && !lastName.isNullOrBlank())
            firstName[0].toUpperCase().toString() + lastName[0].toUpperCase().toString()
        else if (firstName != null && !firstName.isNullOrBlank()) {
            firstName[0].toUpperCase().toString()
        } else {
            "null"
        }
    }
}