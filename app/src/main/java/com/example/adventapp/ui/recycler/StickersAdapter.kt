package com.example.adventapp.ui.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.adventapp.R
import com.example.common.extensions.inflate

internal class StickersAdapter(
    private val onItemClicked: (position: Int) -> Unit
) : ListAdapter<StickersModel, StickersViewHolder>(
    object : DiffUtil.ItemCallback<StickersModel>() {
        override fun areItemsTheSame(oldItem: StickersModel, newItem: StickersModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: StickersModel, newItem: StickersModel): Boolean {
            return oldItem.title == newItem.title
        }
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StickersViewHolder {
        return StickersViewHolder(
            parent.inflate(R.layout.stickers_item),
            onItemClicked
        )
    }

    override fun onBindViewHolder(holder: StickersViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}