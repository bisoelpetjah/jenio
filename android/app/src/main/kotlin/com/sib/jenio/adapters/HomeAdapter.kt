package com.sib.jenio.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.sib.jenio.models.Transaction
import com.sib.jenio.models.User
import com.sib.jenio.utils.DimensionHelper
import com.sib.jenio.views.HomeProfileView
import com.sib.jenio.views.TransactionItemView

/**
 * Created by irvan on 9/30/16.
 */
class HomeAdapter(): RecyclerView.Adapter<HomeAdapter.HomeItemViewHolder>() {

    private val TYPE_PROFILE = 101
    private val TYPE_TRANSACTION = 102
    private val TYPE_LAST = 103

    val transactionList = arrayListOf<Transaction>()
    var user: User? = null

    override fun getItemCount(): Int {
        return transactionList.size + 2
    }

    override fun getItemViewType(position: Int): Int {
        when (position) {
            0 -> return TYPE_PROFILE
            itemCount - 1 -> return TYPE_LAST
            else -> return TYPE_TRANSACTION
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): HomeItemViewHolder {
        when (viewType) {
            TYPE_PROFILE -> {
                val itemView = HomeProfileView(parent!!.context)
                return HomeItemViewHolder(itemView)
            }
            TYPE_TRANSACTION -> {
                val itemView = TransactionItemView(parent!!.context)
                return HomeItemViewHolder(itemView)
            }
            else -> {
                val view = View(parent!!.context)
                view.minimumHeight = DimensionHelper.dpToPx(parent!!.context, 120F).toInt()

                return HomeItemViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: HomeItemViewHolder?, position: Int) {
        when (position) {
            0 -> {
                val homeProfileView = holder!!.itemView as HomeProfileView

                homeProfileView.user = user
            }
            itemCount - 1 -> return
            else -> {
                val transactionItemLayout = holder!!.itemView as TransactionItemView

                transactionItemLayout.transaction = transactionList[position - 1]
            }
        }
    }

    class HomeItemViewHolder(val view: View): RecyclerView.ViewHolder(view)
}