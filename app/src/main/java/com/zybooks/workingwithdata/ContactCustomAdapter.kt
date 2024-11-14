package com.zybooks.workingwithdata

import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView

class ContactCustomAdapter(private val dataSet: ArrayList<Pair<String, String>>) :
    RecyclerView.Adapter<ContactCustomAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView
        val numberTextView: TextView
        var nameWatcher: TextWatcher? = null
        var numberWatcher: TextWatcher? = null

        init {
            // Define click listener for the ViewHolder's View
            nameTextView = view.findViewById(R.id.nameTextView)
            numberTextView = view.findViewById(R.id.numberTextView)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.text_row_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        viewHolder.nameTextView.removeTextChangedListener(viewHolder.nameWatcher)
        viewHolder.nameTextView.doAfterTextChanged {
            text ->
            if ( viewHolder.nameTextView.hasFocus()) {
                if (!text.isNullOrBlank()) {
                    dataSet[position] = Pair(text.toString(), dataSet[position].second)
                }
            }
        }

        viewHolder.numberTextView.removeTextChangedListener(viewHolder.numberWatcher)
        viewHolder.numberTextView.doAfterTextChanged {
                text ->
            if ( viewHolder.numberTextView.hasFocus()) {
                if (!text.isNullOrBlank()) {
                    dataSet[position] = Pair(dataSet[position].first, text.toString())
                    this.notifyDataSetChanged()
                }
            }
        }


        var nameFocus = viewHolder.nameTextView.hasFocus()
        var numberfocus = viewHolder.numberTextView.hasFocus()
        viewHolder.nameTextView.clearFocus()
        viewHolder.numberTextView.clearFocus()

        viewHolder.nameTextView.text = dataSet[position].first
        viewHolder.numberTextView.text = dataSet[position].second

        if (nameFocus ) viewHolder.nameTextView.requestFocus()
        if (numberfocus) viewHolder.numberTextView.requestFocus()
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}