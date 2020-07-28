package ru.skillbranch.devintensive.models

import android.util.Log

class Bender(var status:Status=Status.NORMAL,var question: Question=Question.NAME) {

    fun askQuestion():String = when (question) {
        Question.NAME -> Question.NAME.question
        Question.PROFESSION -> Question.PROFESSION.question
        Question.MATERIAL -> Question.MATERIAL.question
        Question.BDAY -> Question.BDAY.question
        Question.SERIAL -> Question.SERIAL.question
        Question.IDLE -> Question.IDLE.question
    }

    fun listenAnswer(answer:String):Pair<String,Triple<Int,Int,Int>> {
        return if (question.answer.contains(answer.toLowerCase()) && question!=Question.IDLE) {
            question = question.nextQuestion()
            status  = Status.NORMAL
            "Отлично - ты справился\n${question.question}" to status.color
        }
        else if(question==Question.IDLE)
            "На этом все, вопросов больше нет" to status.color
        else{
            status = status.nextStatus()
            "Это неправильный ответ\n${question.question}" to status.color
        }
    }

    enum class Status(var color:Triple<Int,Int,Int>) {
        NORMAL(Triple(255, 255, 255)),
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 255, 0));

        fun nextStatus(): Status {
            return if (ordinal < values().lastIndex) {
                values()[ordinal+1]
            } else {
                values()[0]
            }
        }
    }

    enum class Question(val question:String,val answer: List<String>){
        NAME("Как меня зовут?", listOf("бендер","bender")) {
            override fun validate(answer: String): String {
                return if (answer == "" || !answer[0].isUpperCase()) "Имя должно начинаться с заглавной буквы\n" else ""
            }

            override fun nextQuestion(): Question = PROFESSION
        },
        PROFESSION("Назови мою профессию?", listOf("сгибальщик","bender")) {
            override fun validate(answer: String): String {
                return if (answer == "" || !answer[0].isLowerCase()) "Профессия должна начинаться со строчной буквы\n" else ""
            }

            override fun nextQuestion(): Question = MATERIAL
        },
        MATERIAL("Из чего я сделан?", listOf("металл","дерево","metal","iron","wood")) {
            override fun validate(answer: String): String {
                return if (answer == "" || Regex("[0-9]").containsMatchIn(answer)) "Материал не должен содержать цифр\n" else ""
            }

            override fun nextQuestion(): Question = BDAY
        },
        BDAY("Когда меня создали?", listOf("2993")) {
            override fun validate(answer: String): String {
                return if (answer == "" || Regex("[0-9]+").matchEntire(answer)?.value!=answer) "Год моего рождения должен содержать только цифры\n" else ""
            }

            override fun nextQuestion(): Question = SERIAL
        },
        SERIAL("Мой серийный номер?", listOf("2716057")) {
            override fun validate(answer: String): String {
                return if (answer == "" || Regex("[0-9]+").matchEntire(answer)?.value?.length != 7) "Серийный номер содержит только цифры, и их 7\n" else ""
            }

            override fun nextQuestion(): Question = IDLE
        },
        IDLE("На этом все, вопросов больше нет", listOf()) {
            override fun validate(answer: String): String {
                return ""
            }

            override fun nextQuestion(): Question = IDLE
        };

        abstract fun nextQuestion():Question
        abstract fun validate(answer: String):String
    }
}