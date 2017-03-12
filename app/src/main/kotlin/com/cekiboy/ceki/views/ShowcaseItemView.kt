package com.cekiboy.ceki.views

import android.content.Context
import android.graphics.Bitmap
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.SimpleTarget
import com.cekiboy.ceki.R
import com.cekiboy.ceki.models.Item
import com.cekiboy.ceki.utils.PriceUtils

/**
 * Created by irvan on 9/30/16.
 */
class ShowcaseItemView : RelativeLayout {

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
        nameTextView = findViewById(R.id.merchant) as TextView
        priceTextView = findViewById(R.id.price) as TextView
    }
}