package com.zybooks.workingwithdata

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat

class DataDisplay : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.data_displayed)

        // Define the date format and populate the data
        val dateFormat = SimpleDateFormat("MM-dd-yyyy")
        val roverdata = listOf(
            DataDisplayer(dateFormat.parse("10-31-2089"), "MarsJourneyor", 1000, "aew45tr8aed67wsfied7iqpt"),
            DataDisplayer(dateFormat.parse("01-01-2067"), "Roverroaming", 500, "asrtgdstyhkdfyu43283t7"),
            DataDisplayer(dateFormat.parse("11-26-2347"), "NASA_cam", 250, "aegfuew5i6tr8zqaw7"),
            DataDisplayer(dateFormat.parse("12-25-2500"), "Roverroamer2", 500, "asrtgdstyhkdfyu43283t7"),
            DataDisplayer(dateFormat.parse("01-01-3426"), "NASA_cam2", 250, "aegfuew5i6tr8zqaw7")
        )


        // Set up RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = DataDisplayerAdapter(roverdata)
    }
}