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
                'А' -> result += 'A'
                'Б' -> result += 'B'
                'В' -> result += 'V'
                'Г' -> result += 'G'
                'Д' -> result += 'D'
                'Е' -> result += 'E'
                'Ё' -> result += 'E'
                'Ж' -> result += "Zh"
                'З' -> result += 'Z'
                'И' -> result += 'I'
                'Й' -> result += 'I'
                'К' -> result += 'K'
                'Л' -> result += 'L'
                'М' -> result += 'M'
                'Н' -> result += 'N'
                'О' -> result += 'O'
                'П' -> result += 'P'
                'Р' -> result += 'R'
                'С' -> result += 'S'
                'Т' -> result += 'T'
                'У' -> result += 'U'
                'Ф' -> result += 'F'
                'Х' -> result += 'H'
                'Ц' -> result += 'C'
                'Ч' -> result += "Ch"
                'Ш' -> result += "Sh"
                'Щ' -> result += "Sh"
                'Ъ' -> result += ""
                'Ы' -> result += 'I'
                'Ь' -> result += ""
                'Э' -> result += 'E'
                'Ю' -> result += "Yu"
                'Я' -> result += "Ya"
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