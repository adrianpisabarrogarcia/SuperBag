package com.adriantxupisi.superbag

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import org.w3c.dom.Text
import java.security.Provider

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        // Setup
        setup()
    }

    private fun setup() {
        val bLogin = findViewById<Button>(R.id.buttonLogin)
        val bRegister = findViewById<Button>(R.id.buttonRegister)
        val inputEmail = findViewById<EditText>(R.id.inputEmail)
        val inputPassword = findViewById<EditText>(R.id.inputPassword)
        inputPassword.setText("")

        bRegister.setOnClickListener {
            if(inputEmail.text.isNotEmpty() && inputPassword.text.isNotEmpty()) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(inputEmail.text.toString(), inputPassword.text.toString())
                    .addOnCompleteListener {
                        if(it.isSuccessful) {
                            showSuperBagList(it.result?.user?.email ?: "", ProviderType.BASIC)
                        }else{
                            showAlert()
                        }
                    }
            }
        }


        bLogin.setOnClickListener {
            if(inputEmail.text.isNotEmpty() && inputPassword.text.isNotEmpty()) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(inputEmail.text.toString(), inputPassword.text.toString())
                    .addOnCompleteListener {
                        if(it.isSuccessful) {
                            showSuperBagList(it.result?.user?.email ?: "", ProviderType.BASIC)
                        }else{
                            showAlert()
                        }
                    }
            }
        }



    }


    private fun showAlert(){
        val builder = androidx.appcompat.app.AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: androidx.appcompat.app.AlertDialog = builder.create()
        dialog.show()
    }

    private fun showSuperBagList(email: String, provider: ProviderType){
        val listActivityIntent = Intent(this, ListActivity::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(listActivityIntent)
    }



}