package com.adriantxupisi.superbag

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

class NewItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_item)

        //Setup
        setup()
    }

    private fun setup() {
        //Toolbar initialization
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbarNewItem)
        setSupportActionBar(toolbar)
    }


    //Toolbar menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menutoolbarnewitemactivity, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //Toolbar menu items actions
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.backlist -> {
                goToBackActivity()
            }
            R.id.additem -> {
                saveElement()
                goToBackActivity()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //Go to back activity
    private fun goToBackActivity() {
        onBackPressed()
    }

    //Save element and validate the content
    private fun saveElement() {
        Toast.makeText(this, "Modify", Toast.LENGTH_SHORT).show()

    }






}