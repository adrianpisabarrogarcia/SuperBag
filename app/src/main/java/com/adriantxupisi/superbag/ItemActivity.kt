package com.adriantxupisi.superbag

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import android.widget.Toolbar
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class ItemActivity : AppCompatActivity() {


    private var name: String = ""
    private var description: String = ""
    private var date: String = ""
    private var repeat: String = ""
    private var quantity: String = ""
    private var userEmail: String = ""
    private var documentId: String = ""

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
        product = Product()

        //Initialize intent
        initializeIntentVariables()

        //Toolbar initialization
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbarItem)
        toolbar.title = "🥑 " + product.name
        setSupportActionBar(toolbar)

        //Put the data on the scrren
        putDataOnScreen()

        //Print information of the product
        println("Element: 🥑 $product")
    }

    //Put the data on the screen
    private fun putDataOnScreen() {
        //Get the views
        val name = findViewById<EditText>(R.id.editTextNameModify)
        val description = findViewById<EditText>(R.id.editTextDescriptionModify)
        val date = findViewById<EditText>(R.id.editTextDateModify)
        val repeat = findViewById<CheckBox>(R.id.checkBoxRepeatModify)
        val quantity = findViewById<EditText>(R.id.editTextQuantityModify)

        //Set the data
        name.setText(product.name)
        description.setText(product.description)
        date.setText(product.date)
        if (product.repeat == true) {
            repeat.isChecked = true
        }
        quantity.setText(product.quantity.toString())
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


        //Full the object
        product.name = name
        product.description = description
        product.date = date
        product.repeat = repeat.toBoolean()
        product.quantity = quantity.toInt()
        product.userEmail = userEmail
        product.documentId = documentId

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
        //Toast.makeText(this, "Modify", Toast.LENGTH_SHORT).show()


        //Take all the data from the fields
        val name = findViewById<EditText>(R.id.editTextNameModify).text.toString()
        val description = findViewById<EditText>(R.id.editTextDescriptionModify).text.toString()
        val dateString = findViewById<EditText>(R.id.editTextDateModify).text.toString()
        val repeat = findViewById<CheckBox>(R.id.checkBoxRepeatModify).isChecked()
        val quantity = findViewById<EditText>(R.id.editTextQuantityModify).text.toString()

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
        } catch (e: Exception) {
            validation = false
            Toast.makeText(this, "Please input a correct Date: YYYY-MM-DD", Toast.LENGTH_SHORT)
                .show()
        }

        //Validate the quantity
        try {
            //Parse the quantity to int
            val quantityInt = quantity.toInt()
        } catch (e: Exception) {
            validation = false
            Toast.makeText(this, "Please input a correct quantity", Toast.LENGTH_SHORT).show()
        }


        if (validation) {
            //Create a product
            val product =
                Product(name, description, dateString, repeat, quantity.toInt(), userEmail)
            updateDB(product)
        }

    }

    private fun updateDB(product: Product) {
        //Update the database
        db = FirebaseFirestore.getInstance()
        //update the database
        db.collection("products").document(product.documentId.toString()).update(
            "name", product.name,
            "description", product.description,
            "date", product.date,
            "repeat", product.repeat,
            "quantity", product.quantity
        )
            .addOnSuccessListener {
                //Toast.makeText(this, "Element updated", Toast.LENGTH_SHORT).show()
                goToBackActivity()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error updating element", Toast.LENGTH_SHORT).show()
            }


                /*
            .addOnSuccessListener {
                Toast.makeText(this, "Product updated", Toast.LENGTH_SHORT).show()
                goToBackActivity()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error updating product", Toast.LENGTH_SHORT).show()
            }*/
    }

    //Delete element
    private fun deleteElement() {
        //Delete db
        db = FirebaseFirestore.getInstance()
        db.collection("products").document(product.documentId.toString()).delete()
            .addOnCompleteListener() {
                Toast.makeText(this, "Element deleted", Toast.LENGTH_SHORT).show()
            }
    }


}
