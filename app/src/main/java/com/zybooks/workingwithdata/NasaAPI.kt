package com.zybooks.workingwithdata

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

const val TAG = "NASA_API"

class NasaAPI : AppCompatActivity() {
    lateinit var latEditText: EditText
    lateinit var lonEditText: EditText
    lateinit var dateEditText: EditText
    lateinit var dimEditText: EditText
    lateinit var recyclerView: RecyclerView
    lateinit var imageDataSet: ArrayList<ImageData>
    lateinit var imageCustomAdapter: ImageCustomAdapter

    data class ImageData(val url: String, val date: String)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_nasa_api)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.title = "Earth Assets"

        // Initializing UI components
        latEditText = findViewById(R.id.latEditText)
        lonEditText = findViewById(R.id.lonEditText)
        dateEditText = findViewById(R.id.dateEditText)
        dimEditText = findViewById(R.id.dimEditText)

        // Search button listener
        val searchButton: Button = findViewById(R.id.searchButton)
        searchButton.setOnClickListener {
            searchEarthAssets()
        }

        val clearButton: Button = findViewById(R.id.clearButton)
        clearButton.setOnClickListener {
            clearEditTextFields()
        }

        // Initializing RecyclerView and Adapter
        imageDataSet = arrayListOf()
        imageCustomAdapter = ImageCustomAdapter(imageDataSet)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = imageCustomAdapter
    }

    private fun searchEarthAssets() {
        val baseUrl = "https://api.nasa.gov/planetary/earth/assets"
        var url = "$baseUrl?api_key=2NL58JO2lMZrX48iuwZbzK4JduDZcB8bTHS8XcvF"

        // Get latitude and longitude from user input
        val lat = latEditText.text.toString().toFloatOrNull()
        val lon = lonEditText.text.toString().toFloatOrNull()

        if (lat == null || lon == null) {
            Toast.makeText(this, "Please enter valid latitude and longitude", Toast.LENGTH_LONG).show()
            return
        }

        // Append lat and lon to URL
        url += "&lat=$lat&lon=$lon"

        // Get optional date and dimension from user input
        if (dateEditText.text.isNotEmpty()) {
            val date = dateEditText.text.toString()
            url += "&date=$date"
        }

        val dim = dimEditText.text.toString().toFloatOrNull() ?: 0.025f
        url += "&dim=$dim"

        // Make network request
        val queue: RequestQueue = Volley.newRequestQueue(applicationContext)
        val requestObj = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response -> processEarthAssetsResponse(response) },
            { error -> Log.d(TAG, "Error: $error") }
        )

        queue.add(requestObj)
    }

    private fun processEarthAssetsResponse(response: JSONObject) {
        Log.d(TAG, response.toString())
        imageDataSet.clear() // Clear existing data

        val url = response.getString("url")
        val date = response.optString("date", "No date provided")
        imageDataSet.add(ImageData(url, date))

        imageCustomAdapter.notifyDataSetChanged()
    }

    private fun clearEditTextFields() {
        latEditText.text.clear()
        lonEditText.text.clear()
        dateEditText.text.clear()
        dimEditText.text.clear()
    }
}


