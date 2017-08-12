package com.sib.jenio.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import com.sib.jenio.R

/**
 * Created by itock on 8/12/2017.
 */
class GenerateActivity: AppCompatActivity() {

    private var toolbar: Toolbar? = null
    private var editToken: EditText? = null
    private var buttonSubmit: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate)

        toolbar = findViewById(R.id.toolbar) as Toolbar
        editToken = findViewById(R.id.token) as EditText
        buttonSubmit = findViewById(R.id.submit) as Button

        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(R.string.title_generate)

        buttonSubmit?.setOnClickListener {
            val token = editToken?.text?.toString()
            if (token?.isNullOrEmpty()!!) return@setOnClickListener

            val intent = Intent(this, ResponseActivity::class.java)
            intent.putExtra(ResponseActivity.EXTRA_TRANSACTION_TOKEN, token)

            startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}