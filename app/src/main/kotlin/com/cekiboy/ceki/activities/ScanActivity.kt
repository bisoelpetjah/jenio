package com.cekiboy.ceki.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.Toast
import com.cekiboy.ceki.R
import me.dm7.barcodescanner.zbar.BarcodeFormat
import me.dm7.barcodescanner.zbar.Result
import me.dm7.barcodescanner.zbar.ZBarScannerView

/**
 * Created by irvan on 9/15/16.
 */
class ScanActivity: AppCompatActivity(), ZBarScannerView.ResultHandler {

    private var toolbar: Toolbar? = null
    private var scannerView: ZBarScannerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)

        toolbar = findViewById(R.id.toolbar) as Toolbar
        scannerView = findViewById(R.id.scanner) as ZBarScannerView

        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        scannerView?.setFormats(arrayListOf(BarcodeFormat.QRCODE))
        scannerView?.setResultHandler(this)
    }

    override fun onResume() {
        super.onResume()
        scannerView?.startCamera()
    }

    override fun onPause() {
        super.onPause()
        scannerView?.stopCamera()
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

    override fun handleResult(rawResult: Result?) {
        Toast.makeText(this, rawResult?.contents, Toast.LENGTH_SHORT).show()
        finish()
    }
}