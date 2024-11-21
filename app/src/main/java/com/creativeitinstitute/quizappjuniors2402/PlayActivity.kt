package com.creativeitinstitute.quizappjuniors2402

import android.content.DialogInterface
import android.os.Bundle
import android.os.Message
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.creativeitinstitute.quizappjuniors2402.databinding.ActivityPlayBinding

class PlayActivity : AppCompatActivity() {

    lateinit var binding: ActivityPlayBinding

    var index = 0
    var updateQuestion = 1
    var skip = 0
    var correct = 0
    var wrong = 0
    var finished = false

    val quizList = listOf<Quiz>(

        Quiz(
            "1st Victory Day of Bangladesh?",
            "21 February",
            "16 December",
            "26 March",
            "25 March",
            "16 December"
        ),
        Quiz(
            "International Mother Language Day of Bangladesh?",
            "21 February",
            "16 December",
            "26 March",
            "25 March",
            "21 February"
        ),
        Quiz(
            "2nd Victory Day of Bangladesh?",
            "21 February",
            "16 December",
            "36 July",
            "5 August",
            "5 August"
        ),

        Quiz(
            "Independence Day of Bangladesh?",
            "21 February",
            "16 December",
            "26 March",
            "5 August",
            "26 March"
        )

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initQuestion()

        binding.nextBtn.setOnClickListener {

            showNextQuestion()

        }

    }

    private fun showNextQuestion() {
        checkAnswer()

        binding.apply {

            if (updateQuestion < quizList.size){
                updateQuestion++
                initQuestion()

            }else if (index <= quizList.size -1){

                index++
            }else{

                finished = true
            }

            quizRadioGroup.clearCheck()

        }


    }

    private fun checkAnswer() {

        binding.apply {

            if (quizRadioGroup.checkedRadioButtonId == -1){
                skip++
                showAlertDialog("Skip")

            }else{
                val checkButton = findViewById<RadioButton>(quizRadioGroup.checkedRadioButtonId)
                val checkAnswer = checkButton.text.toString()

                if (checkAnswer==quizList[index].answer){

                    correct++
                    showAlertDialog("Correct Answer")
                }else{
                    wrong++
                    showAlertDialog("wrong Answer")
                }


            }

            if (index<= quizList.size -1){

                index++
            }else{

                Toast.makeText(this@PlayActivity, "Finished Quiz", Toast.LENGTH_SHORT).show()
            }


        }



    }

    private fun initQuestion() {

        val quizQuestion = quizList[index]

        binding.apply {
            questionTV.text = quizQuestion.question
            option1.text = quizQuestion.option1
            option2.text = quizQuestion.option2
            option3.text = quizQuestion.option3
            option4.text = quizQuestion.option4
        }


    }


    fun showAlertDialog(message: String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(message)

        builder.setPositiveButton("Ok",object : DialogInterface.OnClickListener{
            override fun onClick(p0: DialogInterface?, p1: Int) {

            }


        })

        val alertDialog = builder.create()
        alertDialog.show()

    }
}