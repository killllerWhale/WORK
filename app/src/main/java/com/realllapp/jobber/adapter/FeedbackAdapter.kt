package com.realllapp.jobber.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.realllapp.jobber.databinding.ItemReviewBinding
import com.realllapp.jobber.retrofit.dataclasses.Feedback

class FeedbackAdapter(
    private val onItemClick: (Feedback) -> Unit,
) : ListAdapter<Feedback, FeedbackAdapter.DoneWorkViewHolder>(DoneWorkDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoneWorkViewHolder {
        val binding =
            ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DoneWorkViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DoneWorkViewHolder, position: Int) {
        holder.bind(getItem(position) ?: return)
    }

    inner class DoneWorkViewHolder(private val binding: ItemReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val feedback = getItem(position)
                    if (feedback != null) {
                        onItemClick(feedback)
                    }
                }
            }
        }

        fun bind(feedback: Feedback) {
            binding.tvNameEmploy.text = feedback.nameUser
            binding.tvFeedback.text = feedback.feedback
            binding.tvRating.text = feedback.rating.toString()
        }
    }

    object DoneWorkDiffCallback : DiffUtil.ItemCallback<Feedback>() {
        override fun areItemsTheSame(oldItem: Feedback, newItem: Feedback): Boolean {
            return oldItem.feedback == newItem.feedback
        }

        override fun areContentsTheSame(oldItem: Feedback, newItem: Feedback): Boolean {
            return oldItem.feedback == newItem.feedback
        }
    }
}