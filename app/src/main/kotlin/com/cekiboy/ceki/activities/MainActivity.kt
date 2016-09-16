package com.cekiboy.ceki.activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.cekiboy.ceki.R

/**
 * Created by irvan on 9/15/16.
 */
class MainActivity: AppCompatActivity() {

    private var toolbar: Toolbar? = null
    private var buttonScan: FloatingActionButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar) as Toolbar
        buttonScan = findViewById(R.id.buttonScan) as FloatingActionButton

        setSupportActionBar(toolbar)

        buttonScan?.setOnClickListener { startActivity(Intent(this, ScanActivity::class.java)) }
    }
}