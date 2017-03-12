package com.cekiboy.ceki.activities

import android.app.ProgressDialog
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.SimpleTarget
import com.cekiboy.ceki.R
import com.cekiboy.ceki.http.WebService
import com.cekiboy.ceki.models.Item
import com.cekiboy.ceki.models.Transaction
import com.cekiboy.ceki.utils.PreferencesHelper
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
    private var merchantPictureImageView: ImageView? = null
    private var merchantNameTextView: TextView? = null
    private var amountTextView: TextView? = null
    private var amountIncreaseButton: Button? = null
    private var amountDecreaseButton: Button? = null
    private var priceTextView: TextView? = null
    private var buyButton: Button? = null

    private var item: Item? = null
    private var amount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy)

        toolbar = findViewById(R.id.toolbar) as Toolbar
        loadingProgress = findViewById(R.id.loading) as ProgressBar
        contentView = findViewById(R.id.content) as ScrollView
        itemImageView = findViewById(R.id.itemImage) as ImageView
        itemNameTextView = findViewById(R.id.itemName) as TextView
        itemDescriptionTextView = findViewById(R.id.itemDescription) as TextView
        merchantPictureImageView = findViewById(R.id.merchantPicture) as ImageView
        merchantNameTextView = findViewById(R.id.merchantName) as TextView
        amountTextView = findViewById(R.id.amount) as TextView
        amountIncreaseButton = findViewById(R.id.amountIncrease) as Button
        amountDecreaseButton = findViewById(R.id.amountDecrease) as Button
        priceTextView = findViewById(R.id.price) as TextView
        buyButton = findViewById(R.id.buy) as Button

        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(R.string.title_buy)

        amountIncreaseButton?.setOnClickListener {
            if (item?.stock!! > amount) ++amount
            updateAmount()
        }

        amountDecreaseButton?.setOnClickListener {
            if (amount > 1) --amount
            updateAmount()
        }

        buyButton?.setOnClickListener {
            performBuyItem()
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

                Glide.with(this@BuyActivity)
                        .load(item?.merchant?.picture)
                        .asBitmap()
                        .into(object : SimpleTarget<Bitmap>() {
                            override fun onResourceReady(resource: Bitmap?, glideAnimation: GlideAnimation<in Bitmap>?) {
                                val drawable = RoundedBitmapDrawableFactory.create(resources, resource)
                                drawable.isCircular = true

                                merchantPictureImageView?.setImageDrawable(drawable)
                            }
                        })

                merchantNameTextView?.text = item?.merchant?.name

                loadingProgress?.visibility = View.GONE
                contentView?.visibility = View.VISIBLE
                buyButton?.isEnabled = true

                updateAmount()
            }

            override fun onFailure(call: Call<Item>?, t: Throwable?) {}
        })
    }

    private fun performBuyItem() {
        val dialog = ProgressDialog.show(this, null, resources.getString(R.string.progress_buying), true, false)

        WebService.services!!.buyItem(PreferencesHelper.userId, item?.id, amount).enqueue(object : Callback<Transaction> {
            override fun onResponse(call: Call<Transaction>?, response: Response<Transaction>?) {
                dialog.setCancelable(true)
                dialog.cancel()

                Toast.makeText(this@BuyActivity, R.string.buy_successful, Toast.LENGTH_SHORT).show()

                finish()
            }

            override fun onFailure(call: Call<Transaction>?, t: Throwable?) {}
        })
    }
}