package com.cekiboy.ceki.activities

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import com.cekiboy.ceki.R
import com.cekiboy.ceki.http.WebService
import com.cekiboy.ceki.models.Transaction
import com.cekiboy.ceki.utils.PreferencesHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by irvan on 3/11/17.
 */
class NfcActivity: AppCompatActivity() {

    private var toolbar: Toolbar? = null
    private var buyButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nfc)

        toolbar = findViewById(R.id.toolbar) as Toolbar
        buyButton = findViewById(R.id.buy) as Button

        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(R.string.title_nfc)

        buyButton?.setOnClickListener { performBuyItem() }
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

    private fun performBuyItem() {
        val dialog = ProgressDialog.show(this, null, resources.getString(R.string.progress_buying), true, false)

        WebService.services!!.buyItem(PreferencesHelper.userId, "a01cd533-ae18-499e-89bc-4709217d9cb6", 1).enqueue(object : Callback<Transaction> {
            override fun onResponse(call: Call<Transaction>?, response: Response<Transaction>?) {
                dialog.setCancelable(true)
                dialog.cancel()

                Toast.makeText(this@NfcActivity, R.string.buy_successful, Toast.LENGTH_SHORT).show()

                finish()
            }

            override fun onFailure(call: Call<Transaction>?, t: Throwable?) {}
        })
    }
}