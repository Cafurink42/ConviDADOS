package com.example.convidados

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class LoginActivity : AppCompatActivity() {

    lateinit var btnLogin: Button
    lateinit var btnSignUp: TextView
    lateinit var databaseApp: SQLiteDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        createDatabase()

        btnLogin = findViewById(R.id.btnLogin)
        btnLogin.setOnClickListener {
            var i = Intent(this, MainActivity::class.java)
            val emailEditText = findViewById<EditText>(R.id.edtEmailLogin)
            val passwordEditText = findViewById<EditText>(R.id.edtPasswordLogin)
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            login(email, password)
        }

        btnSignUp = findViewById(R.id.txtSignUp)
        btnSignUp.setOnClickListener {
            var i = Intent(this, SignUp::class.java)
            startActivity(i)
        }
    }

    private fun login(email: String, password: String) {
        try {
            databaseApp = openOrCreateDatabase("dbGuestApp", MODE_PRIVATE, null)
            val cursor: Cursor = databaseApp.rawQuery(
                "SELECT email, password FROM userTable WHERE email = '$email' and password= '$password' ",
                null
            )

            if (cursor.moveToFirst()) {
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)

            } else {
                Toast.makeText(this, "Usuário ou senha incorretas", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
    private fun createDatabase(){
        try{
            databaseApp = openOrCreateDatabase("dbGuestApp", MODE_PRIVATE, null)
            databaseApp.execSQL(
                "CREATE TABLE IF NOT EXISTS userTable" +
                "(name varchar, email varchar PRIMARY KEY, password VARCHAR)"
            )
            databaseApp.close()
        }catch(e:Exception){
            e.printStackTrace()
        }
    }

}
