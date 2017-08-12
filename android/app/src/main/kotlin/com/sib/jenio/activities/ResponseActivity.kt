package com.sib.jenio.activities

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.DisplayMetrics
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.sib.jenio.R
import com.sib.jenio.utils.CryptoUtils
import com.sib.jenio.utils.PreferencesHelper

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

        textViewResponse?.setOnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            clipboard.primaryClip = ClipData.newPlainText(null, textViewResponse?.text)
            Toast.makeText(this, "Teks telah disalin ke clipboard.", Toast.LENGTH_SHORT).show()
        }

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
        val secretInString = "RL722DYux4ZIJ0/oIm0Uw51nQ+ofXPtjMjYn7IJksCfz2+Tz9NVV0JerfAzzHD/v1Hxpz+sGeiw/HJ89XQjpbHILm+saLN9Xom0eIMpxVexbthG/YZ3g+ZQmcLCP/pKde3XuzyCwLDdzkPwYfLVP9ElQH07RMBlHfJk0M2nAx44="
        val secret = CryptoUtils.b64d(secretInString)
        val tokenResponse = CryptoUtils.computeTokenResponse(secret, token, PreferencesHelper.userId!!)

        return CryptoUtils.b64e(tokenResponse)
    }

    private fun generateQr(text: String): Bitmap {
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val displayWidth = displayMetrics.widthPixels
        val displayHeight = displayMetrics.heightPixels

        val dimension = (Math.min(displayWidth, displayHeight) * .9).toInt()

        val writer = MultiFormatWriter()
        val result = writer.encode(text, BarcodeFormat.QR_CODE, dimension, dimension, null)

        val width = result.width
        val height = result.height

        val blockColor = ContextCompat.getColor(this, R.color.colorPrimary)
        val spaceColor = ContextCompat.getColor(this, android.R.color.transparent)

        val pixels = IntArray(width * height)

        repeat(height, { y ->
            val offset = y * width

            repeat(width, { x ->
                pixels[offset + x] = if (result[x, y]) blockColor else spaceColor
            })
        })

        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height)

        return bitmap
    }
}