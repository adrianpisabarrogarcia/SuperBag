package com.adriantxupisi.superbag

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import com.google.android.material.appbar.MaterialToolbar
import com.google.firebase.auth.FirebaseAuth

enum class ProviderType {
    BASIC
}

class ListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        //Setup
        val bundle = intent.extras
        val email = bundle?.getString("email")
        val provider = bundle?.getString("provider")
        setup(email ?: "", provider ?: "")

    }

    private fun setup(email: String, provider: String) {
        //Toolbar asignar menu
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)


        //Print information of the user on the console
        println("Email: $email")
        println("Provider: $provider")
    }

    //Toolbar menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menutoolbarlistactivity, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //Toolbar menu items actions
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.cerrarsesion -> {
                signOut()
            }
            R.id.nuevoelemento -> {
                goNewItem()
            }
        }
        return super.onOptionsItemSelected(item)
    }


    //Sign out
    private fun signOut() {
        FirebaseAuth.getInstance().signOut()
        onBackPressed()
    }

    //Go to new item activity to register a new item
    private fun goNewItem() {
        val newItemActivityIntent = Intent(this, NewItemActivity::class.java)
        startActivity(newItemActivityIntent)
    }


}