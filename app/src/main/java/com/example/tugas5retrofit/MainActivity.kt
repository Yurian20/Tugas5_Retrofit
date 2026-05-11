package com.example.tugas5retrofit

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tugas5retrofit.adapter.PasienAdapter
import com.example.tugas5retrofit.api.RetrofitClient
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        getData()
    }

    private fun getData() {

        val prefs = getSharedPreferences("AUTH", MODE_PRIVATE)
        val token = prefs.getString("TOKEN", "")

        lifecycleScope.launch {

            try {

                val response =
                    RetrofitClient.instance.getPasien("Bearer $token")

                if (response.isSuccessful) {

                    val data = response.body()?.data

                    Toast.makeText(
                        this@MainActivity,
                        "Data: ${data?.size}",
                        Toast.LENGTH_LONG
                    ).show()

                    recyclerView.adapter =
                        PasienAdapter(data ?: emptyList())
                }

            } catch (e: Exception) {

                Toast.makeText(
                    this@MainActivity,
                    "Error: ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}