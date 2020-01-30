package com.spm.androidtesting.account

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.spm.androidtesting.R
import com.spm.androidtesting.account.viewmodel.HomeViewModel
import com.spm.androidtesting.databinding.FragmentHomeTestBinding
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

        homeViewModel.setFragmentContext(this)
        binding.viewmodel = homeViewModel

        lifecycle.addObserver(homeViewModel)

        homeViewModel.getBooks()

        return binding.root
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
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
