package com.cekiboy.ceki.views

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.SimpleTarget
import com.cekiboy.ceki.R
import com.cekiboy.ceki.activities.NfcMerchantActivity
import com.cekiboy.ceki.models.Item
import com.cekiboy.ceki.utils.PriceUtils

/**
 * Created by irvan on 9/30/16.
 */
class ShowcaseItemView : RelativeLayout, View.OnClickListener {

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private var pictureImageView: ImageView? = null
    private var nameTextView: TextView? = null
    private var priceTextView: TextView? = null
    private var button: RelativeLayout? = null

    var item: Item? = null
        set(value) {
            field = value

            Glide.with(context)
                    .load(value?.image)
                    .asBitmap()
                    .into(object : SimpleTarget<Bitmap>() {
                        override fun onResourceReady(resource: Bitmap?, glideAnimation: GlideAnimation<in Bitmap>?) {
                            val drawable = RoundedBitmapDrawableFactory.create(resources, resource)
                            drawable.isCircular = true

                            pictureImageView?.setImageDrawable(drawable)
                        }
                    })

            nameTextView?.text = value?.name
            priceTextView?.text = PriceUtils.formatNumberToRupiah(value?.price!!)
        }

    private fun init() {
        LayoutInflater.from(context).inflate(R.layout.layout_showcase_item, this, true)

        pictureImageView = findViewById(R.id.picture) as ImageView
        nameTextView = findViewById(R.id.name) as TextView
        priceTextView = findViewById(R.id.price) as TextView
        button = findViewById(R.id.button) as RelativeLayout

        button?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val intent = Intent(context, NfcMerchantActivity::class.java)
        intent.putExtra(NfcMerchantActivity.EXTRA_ITEM_ID, item?.id)

        context.startActivity(intent)
    }
}