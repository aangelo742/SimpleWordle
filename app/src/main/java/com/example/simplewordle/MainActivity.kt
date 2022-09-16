package com.example.simplewordle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    var wordToGuess = FourLetterWordList.getRandomFourLetterWord()
    var guessCounter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val guessButton = findViewById<Button>(R.id.guessButton)
        val restartButton = findViewById<Button>(R.id.restartButton)
        val editText = findViewById<EditText>(R.id.et_simple)
        val correctWordTxtView = findViewById<TextView>(R.id.correctWordTxtView)

        val guess1UserTxtView = findViewById<TextView>(R.id.guess1UserTxtView)
        val guess1UserCheckTxtView = findViewById<TextView>(R.id.guess1UserCheckTxtView)
        val guess1CheckTxtView = findViewById<TextView>(R.id.guess1CheckTxtView)

        val guess2TxtView = findViewById<TextView>(R.id.guess2TxtView)

        val guess2UserTxtView = findViewById<TextView>(R.id.guess2UserTxtView)
        val guess2UserCheckTxtView = findViewById<TextView>(R.id.guess2UserCheckTxtView)
        val guess2CheckTxtView = findViewById<TextView>(R.id.guess2CheckTxtView)

        val guess3TxtView = findViewById<TextView>(R.id.guess3TxtView)

        val guess3UserTxtView = findViewById<TextView>(R.id.guess3UserTxtView)
        val guess3UserCheckTxtView = findViewById<TextView>(R.id.guess3UserCheckTxtView)
        val guess3CheckTxtView = findViewById<TextView>(R.id.guess3CheckTxtView)

        correctWordTxtView.text = wordToGuess

        restartButton.setOnClickListener {
            guessCounter = 0
            correctWordTxtView.visibility = View.INVISIBLE
            wordToGuess = FourLetterWordList.getRandomFourLetterWord()
            correctWordTxtView.text = wordToGuess

            guess1UserTxtView.visibility = View.INVISIBLE
            guess1UserCheckTxtView.visibility = View.INVISIBLE
            guess1CheckTxtView.visibility = View.INVISIBLE

            guess2TxtView.visibility = View.INVISIBLE
            guess2UserTxtView.visibility = View.INVISIBLE
            guess2UserCheckTxtView.visibility = View.INVISIBLE
            guess2CheckTxtView.visibility = View.INVISIBLE

            guess3TxtView.visibility = View.INVISIBLE
            guess3UserTxtView.visibility = View.INVISIBLE
            guess3UserCheckTxtView.visibility = View.INVISIBLE
            guess3CheckTxtView.visibility = View.INVISIBLE

            restartButton.visibility = View.INVISIBLE
            guessButton.visibility = View.VISIBLE
        }

        guessButton.setOnClickListener {
            if(editText.length() == 4) {
                val userInput = editText.text

                guessCounter++
                if(guessCounter == 1) {


                    guess1UserTxtView.text = userInput.toString().uppercase()
                    guess1UserTxtView.visibility = View.VISIBLE

                    guess1UserCheckTxtView.text = checkGuess(userInput.toString().uppercase())
                    guess1UserCheckTxtView.visibility = View.VISIBLE

                    guess1CheckTxtView.visibility = View.VISIBLE
                    if(guess1UserCheckTxtView.text.toString() != "OOOO") {
                        guess2TxtView.visibility = View.VISIBLE
                    }
                }

                if(guessCounter == 2) {
                    guess2UserTxtView.text = userInput.toString().uppercase()
                    guess2UserTxtView.visibility = View.VISIBLE

                    guess2UserCheckTxtView.text = checkGuess(userInput.toString().uppercase())
                    guess2UserCheckTxtView.visibility = View.VISIBLE

                    guess2CheckTxtView.visibility = View.VISIBLE
                    if(guess2UserCheckTxtView.text.toString() != "OOOO") {

                        guess3TxtView.visibility = View.VISIBLE
                    }
                }

                if(guessCounter == 3) {
                    guess3UserTxtView.text = userInput.toString().uppercase()
                    guess3UserTxtView.visibility = View.VISIBLE

                    guess3UserCheckTxtView.text = checkGuess(userInput.toString().uppercase())
                    guess3UserCheckTxtView.visibility = View.VISIBLE

                    guess3CheckTxtView.visibility = View.VISIBLE
                    if(guess3UserCheckTxtView.text.toString() != "OOOO") {
                        correctWordTxtView.visibility = View.VISIBLE
                        guessButton.visibility = View.INVISIBLE
                        restartButton.visibility = View.VISIBLE
                    }
                }

                if(userInput.toString().uppercase() == wordToGuess) {
                    Toast.makeText(this, "You won in " + guessCounter + "guesses!", Toast.LENGTH_SHORT).show()
                    correctWordTxtView.visibility = View.VISIBLE
                    guessButton.visibility = View.INVISIBLE
                    restartButton.visibility = View.VISIBLE
                }

                userInput.clear()
            }
        }
    }

    /**
     * Parameters / Fields:
     *   wordToGuess : String - the target word the user is trying to guess
     *   guess : String - what the user entered as their guess
     *
     * Returns a String of 'O', '+', and 'X', where:
     *   'O' represents the right letter in the right place
     *   '+' represents the right letter in the wrong place
     *   'X' represents a letter not in the target word
     */

    private fun checkGuess(guess: String) : String {
        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            }
            else if (guess[i] in wordToGuess) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }
}