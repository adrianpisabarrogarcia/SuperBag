package com.adriantxupisi.superbag

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import com.google.firebase.firestore.FirebaseFirestore

class ItemActivity : AppCompatActivity() {


    private lateinit var name: String
    private lateinit var description: String
    private lateinit var date: String
    private lateinit var repeat: String
    private lateinit var quantity: String
    private lateinit var userEmail: String
    private lateinit var documentId: String

    private lateinit var product: Product

    private lateinit var db: FirebaseFirestore




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

        //Setup
        setup()
    }


    //Setup
    private fun setup() {

        //Initialize intent
        initializeIntentVariables()

        //Full the object
        getData()

        //Toolbar initialization
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbarItem)
        toolbar.title = "🥑 "+ product.name
        setSupportActionBar(toolbar)

        //Put the data on the scrren
        putDataOnScreen()

        //Print information of the product
        println("Element: 🥑 ${product.toString()}")
    }

    //Put the data on the screen
    private fun putDataOnScreen() {
        //Get the views
        val name = findViewById<androidx.appcompat.widget.AppCompatTextView>(R.id.name)
        val description = findViewById<androidx.appcompat.widget.AppCompatTextView>(R.id.description)
        val date = findViewById<androidx.appcompat.widget.AppCompatTextView>(R.id.date)
        val repeat = findViewById<androidx.appcompat.widget.AppCompatTextView>(R.id.repeat)
        val quantity = findViewById<androidx.appcompat.widget.AppCompatTextView>(R.id.quantity)

        //Set the data
        name.text = product.name
        description.text = product.description
        date.text = product.date
        repeat.text = product.repeat
        quantity.text = product.quantity.toString()
    }

    //Initialize all values from intent
    private fun initializeIntentVariables() {
        name = intent.getStringExtra("name").toString()
        description = intent.getStringExtra("description").toString()
        date = intent.getStringExtra("date").toString()
        repeat = intent.getStringExtra("repeat").toString()
        quantity = intent.getStringExtra("quantity").toString()
        userEmail = intent.getStringExtra("userEmail").toString()
        documentId = intent.getStringExtra("documentId").toString()
    }

    //Toolbar menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menutoolbaritemactivity, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //Toolbar menu items actions
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.backlist -> {
                goToBackActivity()
            }
            R.id.modify -> {
                saveElement()
                goToBackActivity()
            }
            R.id.delete -> {
                deleteElement()
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

    //Delete element
    private fun deleteElement() {
        //Delete db
        db = FirebaseFirestore.getInstance()
        db.collection("products").document(product.documentId.toString()).delete().addOnCompleteListener() {
            Toast.makeText(this, "Element deleted", Toast.LENGTH_SHORT).show()
        }
    }

    //Take all the data intent to a product element
    private fun getData() {
        product.name = name
        product.description = description
        product.date = date
        product.repeat = repeat.toBoolean()
        product.quantity = quantity.toInt()
        product.userEmail = userEmail
        product.documentId = documentId
    }





}