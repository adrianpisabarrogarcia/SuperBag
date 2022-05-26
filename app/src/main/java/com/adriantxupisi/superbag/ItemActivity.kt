package com.adriantxupisi.superbag

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toolbar

class ItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)


        //Setup
        setup()
    }


    //Setup
    private fun setup() {
        //Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = "🥑 Name Element"
    }
}