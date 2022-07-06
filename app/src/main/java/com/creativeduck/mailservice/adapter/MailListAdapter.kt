package com.creativeduck.mailservice.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.creativeduck.mailservice.R
import com.creativeduck.mailservice.databinding.ItemMailBinding
import java.util.regex.Pattern

class MailListAdapter : ListAdapter<WooWaMail, MailListAdapter.ViewHolder>(MailDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setMail(currentList[position])
    }

    inner class ViewHolder(private val binding: ItemMailBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setMail(item: WooWaMail) {
            binding.apply {
                textMailFrom.text = item.from
                textMailTitle.text = item.title
                textMailContent.text = item.content
                val koPattern = Pattern.compile("[ㄱ-ㅎ가-힣]+")
                val koMatch = koPattern.matcher(item.from)
                val enPattern = Pattern.compile("[a-zA-z]+")
                val enMatch = enPattern.matcher(item.from)
                if (koMatch.find() && koMatch.start() == 0) {
                    imgMailProfile.setImageResource(R.drawable.ic_baseline_person_24)
                    textMailFirst.visibility = View.GONE
                } else if (enMatch.find() && enMatch.start() == 0) {
                    textMailFirst.text = item.from[0].toString()
                    textMailFirst.visibility = View.VISIBLE
                    imgMailProfile.setImageResource(0)
                }
                val rnds = (0..4).random()
                binding.root.context.apply {
                    when(rnds) {
                        0 -> {
                            imgMailProfile.setBackgroundColor(getColor(R.color.skyblue))
                        }
                        1 -> {
                            imgMailProfile.setBackgroundColor(getColor(R.color.blue))
                        }
                        2 -> {
                            imgMailProfile.setBackgroundColor(getColor(R.color.yellow))
                        }
                        3 -> {
                            imgMailProfile.setBackgroundColor(getColor(R.color.green))
                        }
                        4 -> {
                            imgMailProfile.setBackgroundColor(getColor(R.color.purple_200))
                        }
                    }
                }
            }

        }
    }
}