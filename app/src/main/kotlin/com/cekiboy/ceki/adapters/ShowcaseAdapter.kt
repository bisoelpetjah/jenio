package com.cekiboy.ceki.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.cekiboy.ceki.models.Item
import com.cekiboy.ceki.views.ShowcaseItemView

/**
 * Created by irvan on 9/30/16.
 */
class ShowcaseAdapter(): RecyclerView.Adapter<ShowcaseAdapter.ShowcaseItemViewHolder>() {

    val showcaseList = arrayListOf<Item>()

    override fun getItemCount(): Int {
        return showcaseList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ShowcaseItemViewHolder {
        return ShowcaseItemViewHolder(ShowcaseItemView(parent!!.context))
    }

    override fun onBindViewHolder(holder: ShowcaseItemViewHolder?, position: Int) {
        val showcaseItemView = holder!!.itemView as ShowcaseItemView

        showcaseItemView.item = showcaseList[position]
    }

    class ShowcaseItemViewHolder(val view: ShowcaseItemView): RecyclerView.ViewHolder(view)
}