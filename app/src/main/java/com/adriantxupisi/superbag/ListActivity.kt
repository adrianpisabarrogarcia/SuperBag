package com.adriantxupisi.superbag

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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


    private fun setup(email: String, password: String) {
        //Take the material toolbar with a menu
        val materialToolbar = findViewById<MaterialToolbar>(R.id.mToolbar)
        materialToolbar.title = "🛍 SuperBag"

        /*
        logOutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }
         */

    }

}