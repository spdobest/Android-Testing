package com.spm.androidtesting.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.spm.androidtesting.R
import com.spm.androidtesting.account.viewmodel.HomeViewModel
import com.spm.androidtesting.databinding.FragmentHomeTestBinding
import kotlinx.android.synthetic.main.fragment_home_test.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeTestFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var binding: FragmentHomeTestBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_home_test,
                container,
                false
            )

        binding.viewmodel = homeViewModel
        lifecycle.addObserver(homeViewModel)
        if (homeViewModel.bookList.size <= 0) {
            homeViewModel.getBooks()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnUserFragment?.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.userTestFragment, null)
        }
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeTestFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}
