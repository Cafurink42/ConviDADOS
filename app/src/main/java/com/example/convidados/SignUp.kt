package com.example.convidados

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar

class SignUp : AppCompatActivity() {
    lateinit var btnRegister: Button
    lateinit var toolbar: Toolbar
    lateinit var edt_name: EditText
    lateinit var edt_email: EditText
    lateinit var edt_password: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        toolbar = findViewById(R.id.my_toolbarSignUp)
        toolbar.setNavigationOnClickListener {
            finish()
        }

        btnRegister = findViewById(R.id.btnRegister)
        btnRegister.setOnClickListener {
            //salvar
        }
    }
    private fun save(name:String, email:String, password:String) {
        try {
            var databaseApp = openOrCreateDatabase("dbGuestApp", MODE_PRIVATE, null)
            val sql = "INSERT INTO userTable (name, email, password) values ('$name', '$email', '$password')"
            val stmt =  databaseApp.compileStatement(sql)
            stmt.bindString(1, edt_name.text.toString())
            stmt.bindString(2, edt_email.text.toString())
            stmt.bindString(3, edt_password.text.toString())
            stmt.executeInsert()
            databaseApp.close()
        } catch (e:Exception) {
            e.printStackTrace()
        }
        finish()
    }
}