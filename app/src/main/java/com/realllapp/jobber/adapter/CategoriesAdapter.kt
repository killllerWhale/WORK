import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.realllapp.jobber.databinding.ItemCategoriesBinding


class CategoriesAdapter(
    private val onItemClick: (String, Int) -> Unit
) : ListAdapter<Category, CategoriesAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCategoriesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class ViewHolder(private val binding: ItemCategoriesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    onItemClick(item.first, item.second)
                }
            }
        }

        fun bind(item: Category) {
            binding.cardTitle.text = item.first
            binding.cardImage.setImageResource(item.second)
        }
    }

}

private val DiffCallback = object : DiffUtil.ItemCallback<Pair<String, Int>>() {
    override fun areItemsTheSame(
        oldItem: Pair<String, Int>,
        newItem: Pair<String, Int>
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: Pair<String, Int>,
        newItem: Pair<String, Int>
    ): Boolean {
        return oldItem == newItem
    }
}

typealias Category = Pair<String, Int>