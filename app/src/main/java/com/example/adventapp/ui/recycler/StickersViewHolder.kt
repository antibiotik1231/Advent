package com.example.adventapp.ui.recycler

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.adventapp.R

internal class StickersViewHolder(
    view: View,
    private val onItemClicked: (position: Int) -> Unit
) : RecyclerView.ViewHolder(view), View.OnClickListener {

    init {
        view.setOnClickListener(this)
    }

    private val stickersItemTitle = view.findViewById<TextView>(R.id.stickers_item_text_view)

    fun bind(stickers: StickersModel) {
        stickersItemTitle.text = stickers.title
    }

    override fun onClick(view: View?) {
        val position = absoluteAdapterPosition
        onItemClicked(position)
    }
}
