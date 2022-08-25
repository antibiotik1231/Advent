package com.example.adventapp.ui.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.adventapp.R

internal class StickersAdapter(
    private val onItemClicked: (position: Int) -> Unit
) : RecyclerView.Adapter<StickersViewHolder>() {

    var items = emptyList<StickersModel>()

    internal fun setData(items: List<StickersModel>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StickersViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.stickers_item, parent, false)
        return StickersViewHolder(view, onItemClicked)
    }

    override fun onBindViewHolder(holder: StickersViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}