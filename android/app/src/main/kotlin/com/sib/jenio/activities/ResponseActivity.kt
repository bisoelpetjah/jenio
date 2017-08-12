package com.sib.jenio.activities

import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.sib.jenio.R

/**
 * Created by itock on 8/12/2017.
 */
class ResponseActivity: AppCompatActivity() {

    companion object {
        val EXTRA_TRANSACTION_TOKEN = "extra_transaction_token"
    }

    private var toolbar: Toolbar? = null
    private var imageViewQr: ImageView? = null
    private var textViewResponse: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_response)

        toolbar = findViewById(R.id.toolbar) as Toolbar
        imageViewQr = findViewById(R.id.qr) as ImageView
        textViewResponse = findViewById(R.id.response) as TextView

        setSupportActionBar(toolbar)

        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(R.string.title_generate)

        val token = intent.getStringExtra(EXTRA_TRANSACTION_TOKEN)

        if (!token.isNullOrEmpty()) {
            val response = generateTokenResponse(token)
            val bitmap = generateQr(response)

            imageViewQr?.setImageBitmap(bitmap)
            textViewResponse?.text = response
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

    private fun generateTokenResponse(token: String): String {
        // TODO: generate token response
        return token
    }

    private fun generateQr(text: String): Bitmap? {
        // TODO: generate QR code
        return null
    }
}