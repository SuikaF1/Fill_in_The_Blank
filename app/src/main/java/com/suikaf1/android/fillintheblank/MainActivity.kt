package com.suikaf1.android.fillintheblank

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.suikaf1.android.fillintheblank.data.Word
import com.suikaf1.android.fillintheblank.databinding.ActivityMainBinding
import java.lang.StringBuilder
import com.suikaf1.android.fillintheblank.data.wordList

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val localWordList = wordList
    private var wordCount: Int = 0
    private lateinit var currentWord: Word
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updateUI()

        binding.checkButton.setOnClickListener { doesInputMatch() }

        binding.nextButton.setOnClickListener { updateUI() }

        binding.outlinedTextField.editText?.setOnKeyListener { view, keyCode, _ -> handleKeyEvent(view, keyCode)
        }

    }

    private fun putInBlank(word: String): String
    {
        val replaceWithX: Int = (1..(word.length)).random() -1
        val sb = StringBuilder(word).also { it.setCharAt(replaceWithX, 'X') }
        return sb.toString()
    }

    private fun doesInputMatch() {
        val input = binding.outlinedTextField.editText?.text.toString()
        if(getString(currentWord.japaneseWord) == input) {
            Toast.makeText(this, "correct", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "incorrect", Toast.LENGTH_SHORT).show()
        }
    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
    private fun updateUI() {
        if(wordCount < localWordList.size) {
            currentWord = localWordList[wordCount]
            binding.wordInEnglish.text = getString(currentWord.englishTranslation)
            binding.wordInJapanese.text = putInBlank(getString(currentWord.japaneseWord))
            wordCount++
        } else {
            Toast.makeText(this, "Finished!", Toast.LENGTH_SHORT).show()
        }
        binding.outlinedTextField.editText?.text?.clear()
    }
}