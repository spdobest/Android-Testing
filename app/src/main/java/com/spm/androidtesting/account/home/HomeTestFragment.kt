package com.spm.androidtesting.account.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.spm.androidtesting.R
import com.spm.androidtesting.databinding.FragmentHomeTestBinding
import kotlinx.android.synthetic.main.fragment_home_test.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class HomeTestFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(TAG, "onCreate")
        loadKoinModules(homeModule)
        super.onCreate(savedInstanceState)
        Log.i(TAG, "")
    }

    override fun onDestroy() {
        unloadKoinModules(homeModule)
        super.onDestroy()
        Log.i(TAG, "onDestroy")
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.i(TAG, "onCreateView")

        val binding: FragmentHomeTestBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_home_test,
                container,
                false
            )

        binding.viewmodel = homeViewModel
        binding.booksListView.adapter = homeViewModel.bookAdapter

        lifecycle.addObserver(homeViewModel)
        /*    if (homeViewModel.bookList.size <= 0) {
                homeViewModel.getBooks()
            }*/

        return binding.root
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause")
    }

    override fun onDetach() {
        super.onDetach()
        Log.i(TAG, "onDetach")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i(TAG, "onDestroyView")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(TAG, "onViewCreated")
        //  bookadapter.setDeleteListener(homeViewModel)

        /* homeViewModel.getAllBooks().observe(viewLifecycleOwner, Observer {
             homeViewModel.progressVisibility.set(it.isEmpty())
             bookadapter.setData(it)
         })*/

        /* homeViewModel.delete().observe(viewLifecycleOwner, Observer {
             bookadapter.delete(it)
         })*/

        btnUserFragment?.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.userTestFragment, null)
        }
    }

    companion object {
        val TAG = HomeTestFragment::class.java.simpleName
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(/*param1: String, param2: String*/) =
            HomeTestFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}
