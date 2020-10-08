package com.qavan.bender.models

import com.qavan.bender.enum.Question
import com.qavan.bender.enum.Status

class Bender (var status: Status = Status.NORMAL, var question: Question = Question.NAME) {

    fun askQuestion():String = question.question

    fun listenAnswer(answer:String):Pair<String, Int>{
        return when(question){
            Question.IDLE -> question.question to status.color
            else -> "${checkAnswer(answer)}\n${question.question}" to status.color
        }
    }

    private fun checkAnswer(answer: String): String {
        return if (question.answers.contains(answer)) {
            question = question.nextQuestion()
            "Отлично - ты справился"
        }
        else {
            if (status == Status.CRITICAL){
                resetStates()
                "Это неправильный ответ. Давай все по новой"
            }
            else{
                status = status.nextStatus()
                "Это неправильный ответ"
            }
        }
    }

    private fun resetStates() {
        status = Status.NORMAL
        question = Question.NAME
    }
}