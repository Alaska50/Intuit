package com.cc.intuit.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cc.intuit.R
import com.cc.intuit.models.ImageData

class ImageAdapter (
    diffCallback: DiffUtil.ItemCallback<ImageData>,
    private val onClick: (ImageData?) -> Unit
        ) : PagingDataAdapter<ImageData, ImageAdapter.AuthorViewHolder> (diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuthorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder, parent, false)
        return AuthorViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: AuthorViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class AuthorViewHolder(itemView: View, private val onClick: (ImageData?) -> Unit) : RecyclerView.ViewHolder(itemView) {

        private val authorText:TextView = itemView.findViewById(R.id.author_name)
        private var data: ImageData? = null


        fun bind(data: ImageData?) {
            this.data = data
            authorText.text = data?.author ?: ""
            itemView.setOnClickListener {
                onClick(data)
            }
        }

    }
}