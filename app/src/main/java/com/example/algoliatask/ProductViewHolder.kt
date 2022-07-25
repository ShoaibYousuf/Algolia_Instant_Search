package com.example.algoliatask

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.algolia.instantsearch.android.highlighting.toSpannedString

class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val itemName = view.findViewById<TextView>(R.id.itemName)

    fun bind(product: Product) {
        itemName.text = product.highlightedName?.toSpannedString() ?: product.name
       // itemName.text = product.productName
    }
}