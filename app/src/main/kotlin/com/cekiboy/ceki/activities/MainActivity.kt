package com.cekiboy.ceki.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.cekiboy.ceki.R

/**
 * Created by irvan on 9/15/16.
 */
class MainActivity: AppCompatActivity() {

    private var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar) as Toolbar

        setSupportActionBar(toolbar)
    }
}