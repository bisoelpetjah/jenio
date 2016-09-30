package com.cekiboy.ceki.activities

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.bumptech.glide.Glide
import com.cekiboy.ceki.R
import com.cekiboy.ceki.models.Item
import com.cekiboy.ceki.utils.PriceUtils

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

        Handler().postDelayed({
            // TODO: remove dummy
            item = Item()
            item!!.name = "Susu Dancow 1+"
            item!!.image = "http://www.rajasusu.com/image/cache/data/susu%20batita%201-3%20tahun/DANCOW%201+%20VANILA%201000G%20copy-800x800.jpg"
            item!!.description = "DANCOW 1+ adalah susu pertumbuhan untuk anak usia 1-3 tahun dengan kandungan EXCELNUTRI+ yang merupakan inovasi terbaru dari Nestlé Research Centre dengan formula yang telah disempurnakan. Mengandung 3 nutrisi penting untuk perlindungan, perkembangan otak, dan pertumbuhan fisik."
            item!!.price = 10000000
            item!!.stock = 5

            updateAmount()

            Glide.with(this)
                    .load(item?.image)
                    .into(itemImageView)

            itemNameTextView?.text = item?.name
            itemDescriptionTextView?.text = item?.description
            updateAmount()

            loadingProgress?.visibility = View.GONE
            contentView?.visibility = View.VISIBLE
            buyButton?.isEnabled = true
        }, 1000)
    }
}