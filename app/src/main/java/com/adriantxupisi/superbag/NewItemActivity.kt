package com.adriantxupisi.superbag

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.time.DateTimeException
import java.util.*

class NewItemActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore

    private lateinit var userEmail: String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_item)

        //Intent
        val bundle = intent.extras
        userEmail = bundle?.getString("userEmail").toString()

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

        //Take all the data from the fields
        val name = findViewById<EditText>(R.id.editTextNameNewItem).text.toString()
        val quantity = findViewById<EditText>(R.id.editTextQuantityNewItem).text.toString()
        val dateString = findViewById<EditText>(R.id.editTextDateNewItem).text.toString()
        val repeat = findViewById<CheckBox>(R.id.cheackBoxRepeatNewItem).isChecked()
        val description = findViewById<EditText>(R.id.editTextDescriptionNewItem).text.toString()

        var validation = true

        //Validate the content of the fields
        if (name.isEmpty() || quantity.isEmpty() || dateString.isEmpty() || description.isEmpty()) {
            validation = false
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
        }

        //Validate the date
        val date: Date? = null
        try {
            //Parse the dateString to date
            val formatter = SimpleDateFormat("yyyy/MM/dd")
            val date: Date = formatter.parse(dateString)
        } catch (e: Exception){
            validation = false
            Toast.makeText(this, "Please input a correct Date: YYYY-MM-DD", Toast.LENGTH_SHORT).show()
        }

        //Validate the quantity
        try {
            //Parse the quantity to int
            val quantityInt = quantity.toInt()
        } catch (e: Exception){
            validation = false
            Toast.makeText(this, "Please input a correct quantity", Toast.LENGTH_SHORT).show()
        }


        if (validation) {
            //Create a product
            val product = Product(name, description, dateString, repeat, quantity.toInt(), userEmail)
            saveDB(product)
        }

    }

    private fun saveDB(product: Product) {
        db = FirebaseFirestore.getInstance()
        db.collection("products").add(product)
            .addOnSuccessListener {
                Toast.makeText(this, "Product added", Toast.LENGTH_SHORT).show()
                //Go to back activity
                goToBackActivity()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error adding product", Toast.LENGTH_SHORT).show()
            }
    }


}