package com.creativeduck.mailservice.adapter

import androidx.recyclerview.widget.DiffUtil
import java.util.*

data class WooWaMail(
    val type : Int,
    val from : String,
    val title : String,
    val content : String,
    val date : Date
)

class MailDiffUtil : DiffUtil.ItemCallback<WooWaMail>() {
    override fun areItemsTheSame(oldItem: WooWaMail, newItem: WooWaMail): Boolean {
        return oldItem.content == newItem.content
    }

    override fun areContentsTheSame(oldItem: WooWaMail, newItem: WooWaMail): Boolean {
        return oldItem == newItem
    }

}