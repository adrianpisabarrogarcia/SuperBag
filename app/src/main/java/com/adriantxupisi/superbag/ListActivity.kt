package com.adriantxupisi.superbag

import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import kotlin.collections.ArrayList

enum class ProviderType {
    BASIC
}

class ListActivity : AppCompatActivity() {


    private lateinit var recyclerView: RecyclerView
    private lateinit var products: ArrayList<Product>
    private lateinit var productsAdapter: ProductsAdapter
    private lateinit var db: FirebaseFirestore

    private lateinit var userEmail: String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        //Setup
        val bundle = intent.extras
        userEmail = bundle?.getString("email").toString()
        val provider = bundle?.getString("provider")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            setup(userEmail ?: "", provider ?: "")
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setup(email: String, provider: String) {
        //Toolbar initialization
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        //RecyclerView initialization
        recyclerView = findViewById<RecyclerView>(R.id.listProductsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        //List of Products
        products = arrayListOf()

        //Adapter of Productos
        productsAdapter = ProductsAdapter(products)

        //Add products to the list
        recyclerView.adapter = productsAdapter


        EventChangeListener()


        //Print information of the user on the console
        println("Email: $email")
        println("Provider: $provider")
    }

    //Add products to the list
    private fun EventChangeListener() {
        //Listener for the products
        db = FirebaseFirestore.getInstance()
        db.collection("products").addSnapshotListener { querySnapshot, firebaseFirestoreException ->
            if (firebaseFirestoreException != null) {
                println("Error: ${firebaseFirestoreException.message}")
            } else {
                products.clear()
                for (document in querySnapshot!!) {
                    val product = document.toObject(Product::class.java)
                    products.add(product)
                }
                productsAdapter.notifyDataSetChanged()
            }
        }
    }

    //Toolbar menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menutoolbarlistactivity, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //Toolbar menu items actions
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.signout -> {
                signOut()
            }
            R.id.new_element -> {
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
        val newItemActivityIntent = Intent(this, NewItemActivity::class.java).apply {
            //Send the userEmail to the new item activity
            putExtra("userEmail", userEmail)
        }
        startActivity(newItemActivityIntent)
    }


}