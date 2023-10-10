package com.realllapp.jobber.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.realllapp.jobber.databinding.ItemDoneBinding
import com.realllapp.jobber.retrofit.dataclasses.DoneCard

class DoneWorkAdapter(
    private val onItemClick: (DoneCard) -> Unit,
) : ListAdapter<DoneCard, DoneWorkAdapter.DoneWorkViewHolder>(DoneWorkDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoneWorkViewHolder {
        val binding =
            ItemDoneBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DoneWorkViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DoneWorkViewHolder, position: Int) {
        holder.bind(getItem(position) ?: return)
    }

    inner class DoneWorkViewHolder(private val binding: ItemDoneBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val doneCard = getItem(position)
                    if (doneCard != null) {
                        onItemClick(doneCard)
                    }
                }
            }
        }

        fun bind(doneCard: DoneCard) {
            binding.tvTitleWork.text = doneCard.titleWork
            binding.tvTitleWork.text = doneCard.geo
            binding.tvEmployerName.text = doneCard.nameEmployer
            binding.tvRating.text = doneCard.rating.toString()
        }
    }

    object DoneWorkDiffCallback : DiffUtil.ItemCallback<DoneCard>() {
        override fun areItemsTheSame(oldItem: DoneCard, newItem: DoneCard): Boolean {
            return oldItem.titleWork == newItem.titleWork
        }

        override fun areContentsTheSame(oldItem: DoneCard, newItem: DoneCard): Boolean {
            return oldItem.titleWork == newItem.titleWork
        }
    }
}