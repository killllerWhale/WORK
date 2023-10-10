import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.realllapp.jobber.adapter.DoneWorkAdapter
import com.realllapp.jobber.adapter.FeedbackAdapter
import com.realllapp.jobber.databinding.FragmentReviewsBinding
import com.realllapp.jobber.fragments.ViewBindingFragment
import com.realllapp.jobber.retrofit.dataclasses.DoneCard
import com.realllapp.jobber.retrofit.dataclasses.Feedback

class ReviewsFragment : ViewBindingFragment<FragmentReviewsBinding>() {

//    private val vm by lazy {
//        ViewModelProvider(this)[StartVM::class.java]
//    }

    override fun makeBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentReviewsBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //vm.createDb(requireContext())

        val adapterFeedback = FeedbackAdapter { item ->
            Toast.makeText(
                requireContext(),
                "Нажата карточка: ${item.nameUser}",
                Toast.LENGTH_SHORT
            )
                .show()
        }

        binding.rvReviews.run {
            this.adapter = adapterFeedback
        }

        val feedbackList = mutableListOf<Feedback>()

        for (i in 1..10) {
            val row = Feedback(
                nameUser = "User $i",
                feedback = "Отзыв $i",
                rating = "3.$i".toDouble(),
            )
            feedbackList.add(row)
        }

        adapterFeedback.submitList(feedbackList)
    }
}