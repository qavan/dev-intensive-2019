package com.qavan.bender.enum

enum class Question(val question: String, val answers: List<String>) {
    NAME("Как меня зовут?", listOf("бендер", "bender")) {
        override fun nextQuestion(): Question = PROFESSION
        override fun validate(answer: String): Boolean = answer.trim().firstOrNull()?.isUpperCase() ?: false
    },
    PROFESSION("Назови мою профессию?", listOf("сгибальщик", "bender")) {
        override fun nextQuestion(): Question = MATERIAL
        override fun validate(answer: String): Boolean = answer.trim().firstOrNull()?.isLowerCase() ?: false
    },
    MATERIAL("Из чего я сделан?", listOf("металл", "дерево", "iron", "wood", "metal")) {
        override fun nextQuestion(): Question = BIRTHDAY
        override fun validate(answer: String): Boolean = answer.trim().contains(Regex("\\d")).not()
    },
    BIRTHDAY("Когда меня создали?", listOf("2993")) {
        override fun nextQuestion(): Question = SERIAL
        override fun validate(answer: String): Boolean = answer.trim().contains(Regex("^[0-9]*$"))
    },
    SERIAL("Мой серийный номер?", listOf("2716057")) {
        override fun nextQuestion(): Question = IDLE
        override fun validate(answer: String): Boolean = answer.trim().contains(Regex("^[0-9]{7}$"))
    },
    IDLE("На этом все, вопросов больше нет", listOf()) {
        override fun nextQuestion(): Question = IDLE
        override fun validate(answer: String): Boolean = true
    };

    abstract fun nextQuestion():Question
    abstract fun validate(answer: String):Boolean
}