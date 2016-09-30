package com.cekiboy.ceki.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import android.widget.TextView
import com.cekiboy.ceki.R
import com.cekiboy.ceki.models.Transaction
import com.cekiboy.ceki.utils.PriceUtils
import com.cekiboy.ceki.utils.TimeUtils

/**
 * Created by irvan on 9/30/16.
 */
class TransactionItemView : RelativeLayout {

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private var itemTextView: TextView? = null
    private var merchantTextView: TextView? = null
    private var priceTextView: TextView? = null
    private var timeTextView: TextView? = null

    var transaction: Transaction? = null
        set(value) {
            field = value

            itemTextView?.text = value?.item?.name
            merchantTextView?.text = value?.item?.merchant?.name
            priceTextView?.text = PriceUtils.formatNumberToRupiah(value?.amount!! * value?.item?.price!!)
            timeTextView?.text = TimeUtils.getRelativeTimeSpan(value?.timestamp)
        }

    private fun init() {
        LayoutInflater.from(context).inflate(R.layout.layout_transaction_item, this, true)

        itemTextView = findViewById(R.id.item) as TextView
        merchantTextView = findViewById(R.id.merchant) as TextView
        priceTextView = findViewById(R.id.price) as TextView
        timeTextView = findViewById(R.id.time) as TextView
    }
}