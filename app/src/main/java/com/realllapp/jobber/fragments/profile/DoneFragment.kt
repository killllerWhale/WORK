import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.realllapp.jobber.adapter.DoneWorkAdapter
import com.realllapp.jobber.databinding.FragmentDoneBinding
import com.realllapp.jobber.fragments.ViewBindingFragment
import com.realllapp.jobber.retrofit.dataclasses.DoneCard

class DoneFragment : ViewBindingFragment<FragmentDoneBinding>() {

//    private val vm by lazy {
//        ViewModelProvider(this)[StartVM::class.java]
//    }

    override fun makeBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentDoneBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //vm.createDb(requireContext())

        val adapterDoneWork = DoneWorkAdapter { item ->
            Toast.makeText(
                requireContext(),
                "Нажата карточка: ${item.titleWork}",
                Toast.LENGTH_SHORT
            )
                .show()
        }

        binding.rvDoneWork.run {
            this.adapter = adapterDoneWork
        }

        val doneWorkList = mutableListOf<DoneCard>()

        for (i in 1..10) {
            val row = DoneCard(
                titleWork = "Work $i",
                geo = "Geo $i",
                nameEmployer = "Employer $i",
                rating = "3.$i".toDouble(),
            )
            doneWorkList.add(row)
        }

        adapterDoneWork.submitList(doneWorkList)
    }
}