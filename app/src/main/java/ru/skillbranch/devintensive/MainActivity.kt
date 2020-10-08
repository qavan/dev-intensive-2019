package ru.skillbranch.devintensive

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import ru.skillbranch.devintensive.models.Bender
import android.view.inputmethod.EditorInfo
import android.text.InputType
import ru.skillbranch.devintensive.enum.Question
import ru.skillbranch.devintensive.enum.Status
import java.util.*


class MainActivity : AppCompatActivity() {

    private var benderObj: Bender = Bender()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        iv_bender.setColorFilter(resources.getColor(benderObj.status.color), PorterDuff.Mode.MULTIPLY)

        tv_text.text = benderObj.askQuestion()

        iv_send.setOnClickListener {
            if (isAnswerValid())
                sendAnswer()
            else
                makeErrorMessage()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun makeErrorMessage() {
        val errorMessage = when (benderObj.question) {
            Question.NAME -> "Имя должно начинаться с заглавной буквы"
            Question.PROFESSION -> "Профессия должна начинаться со строчной буквы"
            Question.MATERIAL -> "Материал не должен содержать цифр"
            Question.BIRTHDAY -> "Год моего рождения должен содержать только цифры"
            Question.SERIAL -> "Серийный номер содержит только цифры, и их 7"
            else -> "На этом все, вопросов больше нет"
        }
        tv_text.text = "$errorMessage ${benderObj.question.question}"
        et_message.setText("")
    }

    private fun isAnswerValid(): Boolean {
        return benderObj.question.validate(et_message.text.toString())
    }

    private fun sendAnswer() {
        val (phase, color) = benderObj.listenAnswer(et_message.text.toString().toLowerCase(Locale("ru")))
        et_message.setText("")
        iv_bender.setColorFilter(resources.getColor(color), PorterDuff.Mode.MULTIPLY)
        tv_text.text = phase
    }

}