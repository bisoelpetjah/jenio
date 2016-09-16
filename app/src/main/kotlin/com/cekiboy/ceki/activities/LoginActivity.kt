package com.cekiboy.ceki.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.cekiboy.ceki.R

/**
 * Created by irvan on 9/16/16.
 */
class LoginActivity: AppCompatActivity() {

    private var textViewPin: TextView? = null
    private var buttonKeypad1: Button? = null
    private var buttonKeypad2: Button? = null
    private var buttonKeypad3: Button? = null
    private var buttonKeypad4: Button? = null
    private var buttonKeypad5: Button? = null
    private var buttonKeypad6: Button? = null
    private var buttonKeypad7: Button? = null
    private var buttonKeypad8: Button? = null
    private var buttonKeypad9: Button? = null
    private var buttonKeypad0: Button? = null
    private var buttonKeypadBackspace: ImageButton? = null
    private var buttonKeypadEnter: ImageButton? = null

    private var pin = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        textViewPin = findViewById(R.id.pin) as TextView
        buttonKeypad1 = findViewById(R.id.keypad1) as Button
        buttonKeypad2 = findViewById(R.id.keypad2) as Button
        buttonKeypad3 = findViewById(R.id.keypad3) as Button
        buttonKeypad4 = findViewById(R.id.keypad4) as Button
        buttonKeypad5 = findViewById(R.id.keypad5) as Button
        buttonKeypad6 = findViewById(R.id.keypad6) as Button
        buttonKeypad7 = findViewById(R.id.keypad7) as Button
        buttonKeypad8 = findViewById(R.id.keypad8) as Button
        buttonKeypad9 = findViewById(R.id.keypad9) as Button
        buttonKeypad0 = findViewById(R.id.keypad0) as Button
        buttonKeypadBackspace = findViewById(R.id.keypadBackspace) as ImageButton
        buttonKeypadEnter = findViewById(R.id.keypadEnter) as ImageButton

        buttonKeypad1?.setOnClickListener { editInput(ButtonType.BUTTON_NUMBER, "1") }
        buttonKeypad2?.setOnClickListener { editInput(ButtonType.BUTTON_NUMBER, "2") }
        buttonKeypad3?.setOnClickListener { editInput(ButtonType.BUTTON_NUMBER, "3") }
        buttonKeypad4?.setOnClickListener { editInput(ButtonType.BUTTON_NUMBER, "4") }
        buttonKeypad5?.setOnClickListener { editInput(ButtonType.BUTTON_NUMBER, "5") }
        buttonKeypad6?.setOnClickListener { editInput(ButtonType.BUTTON_NUMBER, "6") }
        buttonKeypad7?.setOnClickListener { editInput(ButtonType.BUTTON_NUMBER, "7") }
        buttonKeypad8?.setOnClickListener { editInput(ButtonType.BUTTON_NUMBER, "8") }
        buttonKeypad9?.setOnClickListener { editInput(ButtonType.BUTTON_NUMBER, "9") }
        buttonKeypad0?.setOnClickListener { editInput(ButtonType.BUTTON_NUMBER, "0") }
        buttonKeypadBackspace?.setOnClickListener { editInput(ButtonType.BUTTON_BACKSPACE) }
        buttonKeypadEnter?.setOnClickListener { editInput(ButtonType.BUTTON_ENTER) }
    }

    private fun editInput(pressedButton: ButtonType, buttonNumber: String? = null) {
        val isEmpty = textViewPin?.text?.isEmpty()
        val isFull = textViewPin?.text?.length == 4

        when (pressedButton) {
            ButtonType.BUTTON_NUMBER -> {
                if (!isFull) {
                    pin = "$pin$buttonNumber"
                    textViewPin?.text = "${textViewPin?.text}*"
                }
            }
            ButtonType.BUTTON_BACKSPACE -> {
                if (!isEmpty!!) {
                    pin = pin.substring(0, pin.length - 1)
                    textViewPin?.text = textViewPin?.text?.substring(0, textViewPin?.text?.length!! - 1)
                }
            }
            ButtonType.BUTTON_ENTER -> submit()
        }
        Log.d("@@@", pin)
    }

    private fun submit() {
        if (pin == "1589") {
            finish()
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            pin = ""
            textViewPin?.text = ""
        }
    }

    private enum class ButtonType {
        BUTTON_NUMBER,
        BUTTON_BACKSPACE,
        BUTTON_ENTER
    }
}