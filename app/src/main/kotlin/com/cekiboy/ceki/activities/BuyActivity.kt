package com.cekiboy.ceki.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.bumptech.glide.Glide
import com.cekiboy.ceki.R
import com.cekiboy.ceki.http.WebService
import com.cekiboy.ceki.models.Item
import com.cekiboy.ceki.utils.PriceUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by irvan on 9/29/16.
 */
class BuyActivity: AppCompatActivity() {

    companion object {
        val EXTRA_ITEM_ID = "item_id"
    }

    private var toolbar: Toolbar? = null
    private var loadingProgress: ProgressBar? = null
    private var contentView: ScrollView? = null
    private var itemImageView: ImageView? = null
    private var itemNameTextView: TextView? = null
    private var itemDescriptionTextView: TextView? = null
    private var amountTextView: TextView? = null
    private var amountIncreaseButton: Button? = null
    private var amountDecreaseButton: Button? = null
    private var priceTextView: TextView? = null
    private var buyButton: Button? = null

    private var item: Item? = null
    private var amount = 1L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy)

        toolbar = findViewById(R.id.toolbar) as Toolbar
        loadingProgress = findViewById(R.id.loading) as ProgressBar
        contentView = findViewById(R.id.content) as ScrollView
        itemImageView = findViewById(R.id.itemImage) as ImageView
        itemNameTextView = findViewById(R.id.itemName) as TextView
        itemDescriptionTextView = findViewById(R.id.itemDescription) as TextView
        amountTextView = findViewById(R.id.amount) as TextView
        amountIncreaseButton = findViewById(R.id.amountIncrease) as Button
        amountDecreaseButton = findViewById(R.id.amountDecrease) as Button
        priceTextView = findViewById(R.id.price) as TextView
        buyButton = findViewById(R.id.buy) as Button

        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = null

        amountIncreaseButton?.setOnClickListener {
            if (item?.stock!! > amount) ++amount
            updateAmount()
        }
        amountDecreaseButton?.setOnClickListener {
            if (amount > 1) --amount
            updateAmount()
        }

        performFetchItemDetail(intent.getStringExtra(EXTRA_ITEM_ID))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.buy, menu)
        return true
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

    private fun updateAmount() {
        amountTextView?.text = "$amount"
        priceTextView?.text = PriceUtils.formatNumberToRupiah(item?.price!! * amount)
    }

    private fun performFetchItemDetail(id: String?) {
        loadingProgress?.visibility = View.VISIBLE
        contentView?.visibility = View.INVISIBLE
        buyButton?.isEnabled = false

        WebService.services!!.getItem(id).enqueue(object : Callback<Item> {
            override fun onResponse(call: Call<Item>?, response: Response<Item>?) {
                item = response?.body()

                Glide.with(this@BuyActivity)
                        .load(item?.image)
                        .into(itemImageView)

                itemNameTextView?.text = item?.name
                itemDescriptionTextView?.text = item?.description
                updateAmount()

                loadingProgress?.visibility = View.GONE
                contentView?.visibility = View.VISIBLE
                buyButton?.isEnabled = true

                updateAmount()
            }

            override fun onFailure(call: Call<Item>?, t: Throwable?) {}
        })
    }
}