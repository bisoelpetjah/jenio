package com.cekiboy.ceki.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.cekiboy.ceki.R
import com.cekiboy.ceki.adapters.ShowcaseAdapter
import com.cekiboy.ceki.http.WebService
import com.cekiboy.ceki.models.Item
import com.cekiboy.ceki.models.Transaction
import com.cekiboy.ceki.utils.PreferencesHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by irvan on 3/11/17.
 */
class MerchantActivity: AppCompatActivity() {

    private var toolbar: Toolbar? = null
    private var recyclerView: RecyclerView? = null

    private val showcaseAdapter = ShowcaseAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_merchant)

        toolbar = findViewById(R.id.toolbar) as Toolbar
        recyclerView = findViewById(R.id.recycler) as RecyclerView

        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(R.string.title_showcase)

        recyclerView?.adapter = showcaseAdapter
        recyclerView?.layoutManager = LinearLayoutManager(this)

        performGetShowcase()
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

    private fun performGetShowcase() {
        WebService.services!!.getShowcase(PreferencesHelper.userId).enqueue(object : Callback<List<Item>> {
            override fun onResponse(call: Call<List<Item>>?, response: Response<List<Item>>?) {
                showcaseAdapter.showcaseList.clear()
                showcaseAdapter.showcaseList.addAll(response?.body() ?: arrayListOf())
                showcaseAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<Item>>?, t: Throwable?) {}
        })
    }
}