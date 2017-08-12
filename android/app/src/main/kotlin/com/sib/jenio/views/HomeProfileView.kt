package com.sib.jenio.views

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
import com.sib.jenio.R
import com.sib.jenio.models.User
import com.sib.jenio.utils.PriceUtils

/**
 * Created by irvan on 9/30/16.
 */
class HomeProfileView: RelativeLayout {

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
    private var emailTextView: TextView? = null
    private var balanceTextView: TextView? = null

    var user: User? = null
        set(value) {
            field = value

            Glide.with(context)
                    .load(value?.picture)
                    .asBitmap()
                    .into(object : SimpleTarget<Bitmap>() {
                        override fun onResourceReady(resource: Bitmap?, glideAnimation: GlideAnimation<in Bitmap>?) {
                            val drawable = RoundedBitmapDrawableFactory.create(resources, resource)
                            drawable.isCircular = true

                            pictureImageView?.setImageDrawable(drawable)
                        }
                    })

            nameTextView?.text = value?.name
            emailTextView?.text = value?.email

            if (value?.balance != null) balanceTextView?.text = PriceUtils.formatNumberToRupiah(value?.balance!!)
        }

    private fun init() {
        LayoutInflater.from(context).inflate(R.layout.layout_home_profile, this, true)

        pictureImageView = findViewById(R.id.picture) as ImageView
        nameTextView = findViewById(R.id.name) as TextView
        emailTextView = findViewById(R.id.email) as TextView
        balanceTextView = findViewById(R.id.balance) as TextView
    }
}