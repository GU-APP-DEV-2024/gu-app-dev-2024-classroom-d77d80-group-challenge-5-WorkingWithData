package com.zybooks.workingwithdata

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat

class DataDisplayerAdapter(private val dataList: List<DataDisplayer>) : RecyclerView.Adapter<DataDisplayerAdapter.ViewHolder>() {

    // Define ViewHolder
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dateTextView: TextView = itemView.findViewById(R.id.date_text)
        val cameraTextView: TextView = itemView.findViewById(R.id.camera_text)
        val pageTextView: TextView = itemView.findViewById(R.id.page_text)
        val apiKeyTextView: TextView = itemView.findViewById(R.id.api_key_text)
    }

    // Inflate the row layout (item_data_displayer.xml) for each item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_data_displayer, parent, false)
        return ViewHolder(itemView)
    }

    // Bind the data to the views
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]
        val dateFormat = SimpleDateFormat("MM-dd-yyyy")

        // Set the data to the respective views
        holder.dateTextView.text = dateFormat.format(data.earth_day)
        holder.cameraTextView.text = data.cam
        holder.pageTextView.text = data.page.toString()
        holder.apiKeyTextView.text = data.api_key
    }

    // Return the number of items in the data list
    override fun getItemCount(): Int {
        return dataList.size
    }
}


