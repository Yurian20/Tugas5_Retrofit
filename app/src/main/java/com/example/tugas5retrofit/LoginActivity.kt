package com.example.tugas5retrofit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.tugas5retrofit.api.RetrofitClient
import com.example.tugas5retrofit.model.LoginRequest
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etEmail = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {

            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            lifecycleScope.launch {

                try {

                    val response = RetrofitClient.instance.login(
                        LoginRequest(email, password)
                    )

                    if (response.isSuccessful) {

                        val token = response.body()?.data?.token

                        val prefs = getSharedPreferences("AUTH", MODE_PRIVATE)
                        prefs.edit().putString("TOKEN", token).apply()

                        Toast.makeText(
                            this@LoginActivity,
                            "Login Berhasil",
                            Toast.LENGTH_SHORT
                        ).show()

                        startActivity(
                            Intent(
                                this@LoginActivity,
                                MainActivity::class.java
                            )
                        )

                    } else {

                        Toast.makeText(
                            this@LoginActivity,
                            "Login gagal",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                } catch (e: Exception) {

                    Toast.makeText(
                        this@LoginActivity,
                        e.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}