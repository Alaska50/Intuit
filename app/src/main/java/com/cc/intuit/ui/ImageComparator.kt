package com.cc.intuit.ui

import androidx.recyclerview.widget.DiffUtil
import com.cc.intuit.models.ImageData

object ImageComparator : DiffUtil.ItemCallback<ImageData>() {

    override fun areItemsTheSame(oldItem: ImageData, newItem: ImageData): Boolean {
        return oldItem.downloadURL == newItem.downloadURL
    }

    override fun areContentsTheSame(oldItem: ImageData, newItem: ImageData): Boolean {
        return oldItem == newItem
    }
}