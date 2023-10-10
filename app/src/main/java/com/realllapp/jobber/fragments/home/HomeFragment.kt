package com.realllapp.jobber.fragments.home


import CategoriesAdapter
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.realllapp.jobber.R
import com.realllapp.jobber.adapter.CurrentWorkAdapter
import com.realllapp.jobber.databinding.FragmentHomeBinding
import com.realllapp.jobber.dialogs.WorkDialog
import com.realllapp.jobber.fragments.ViewBindingFragment
import com.realllapp.jobber.retrofit.dataclasses.Row
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : ViewBindingFragment<FragmentHomeBinding>() {

    private val vm: HomeVM by viewModel()
    private val SCROLL_THRESHOLD_DP1 = 0
    private val SCROLL_THRESHOLD_DP2 = 1000
    private val SCROLL_THRESHOLD_DP3 = 1020
    private val SCROLL_THRESHOLD_DP4 = 2000
    var isAppBarExpanded = true

    private val workDialog: WorkDialog by lazy {
        WorkDialog(requireContext())
    }

    override fun makeBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHomeBinding.inflate(inflater)

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.appBarLayout.visibility = View.GONE

        binding.nsHome.setOnScrollChangeListener { _, _, scrollY, _, _ ->
            if (scrollY in SCROLL_THRESHOLD_DP1..SCROLL_THRESHOLD_DP2) {
                executeCodeInOrder(
                    {
                        binding.appBarLayout.setExpanded(false, true)
                    },
                    {
                        binding.appBarLayout.postDelayed(
                            {
                                binding.appBarLayout.isVisible = false
                            },
                            1
                        )
                    }
                )
                isAppBarExpanded = true
            } else if (isAppBarExpanded && scrollY in SCROLL_THRESHOLD_DP3..SCROLL_THRESHOLD_DP4) {
                binding.appBarLayout.isVisible = true
                isAppBarExpanded = false
            }
        }
        vm.nameUser
            .onEach {
                binding.tvNameUser.text = it
            }
            .launchIn(lifecycleScope)

        val adapterCategories = CategoriesAdapter { categoryName, _ ->
            Toast.makeText(requireContext(), "Нажата карточка: $categoryName", Toast.LENGTH_SHORT)
                .show()
        }

        val adapterCurrentWork = CurrentWorkAdapter { row ->
            workDialog.show()
        }

        binding.rvCategories.run {
            this.adapter = adapterCategories
        }

        binding.rvCurrentWork.run {
            this.adapter = adapterCurrentWork
        }

        val rowList = mutableListOf<Row>()

        for (i in 1..10) {
            val row = Row(
                category = "Category $i",
                data_reg = "Date $i",
                description = "Description $i",
                id = "$i",
                id_user = "User $i",
                latitude = "Latitude $i",
                longitude = "Longitude $i",
                name = "Name $i",
                price = "Price $i",
                image = "Image $i",
                type = "Type $i"
            )
            rowList.add(row)
        }

        val pairList = listOf(
            Pair("Стройка", R.drawable.icon_default_categories),
            Pair("Сад", R.drawable.icon_default_categories),
            Pair("Ванная", R.drawable.icon_default_categories),
            Pair("Душ", R.drawable.icon_default_categories),
            Pair("Стол", R.drawable.icon_default_categories),
            Pair("Универ", R.drawable.icon_default_categories),
            Pair("Сад", R.drawable.icon_default_categories),
        )

        adapterCategories.submitList(pairList)
        adapterCurrentWork.submitList(rowList)
    }

    private fun executeCodeInOrder(vararg actions: () -> Unit) {
        for (action in actions) {
            action.invoke()
        }
    }
}