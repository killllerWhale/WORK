package com.realllapp.jobber.adapter

import com.realllapp.jobber.retrofit.dataclasses.Row
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.realllapp.jobber.databinding.ItemCurrentWorkBinding

    class CurrentWorkAdapter(
        private val onItemClick: (Row) -> Unit,
    ) : ListAdapter<Row, CurrentWorkAdapter.RowViewHolder>(RowDiffCallback) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowViewHolder {
            val binding =
                ItemCurrentWorkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return RowViewHolder(binding)
        }

        override fun onBindViewHolder(holder: RowViewHolder, position: Int) {
            holder.bind(getItem(position) ?: return)
        }

        inner class RowViewHolder(private val binding: ItemCurrentWorkBinding) :
            RecyclerView.ViewHolder(binding.root) {

            init {
                binding.root.setOnClickListener {
                    val position = bindingAdapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val row = getItem(position)
                        if (row != null) {
                            onItemClick(row)
                        }
                    }
                }
            }

            fun bind(row: Row) {
                binding.tvNameWork.text = row.name
//                binding.cardImage.load("${RetrofitUrls.IMAGE_URL}${row.poster_path}") {
//                    memoryCachePolicy(CachePolicy.ENABLED)
//                }
            }
        }

        object RowDiffCallback : DiffUtil.ItemCallback<Row>() {
            override fun areItemsTheSame(oldItem: Row, newItem: Row): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Row, newItem: Row): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }