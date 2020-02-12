package com.spm.androidtesting.ui.fragment


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.spm.androidtesting.R
import com.spm.androidtesting.databinding.FragmentTabcontainerBinding
import com.spm.androidtesting.ui.adapter.ViewpagerStateAdapter
import com.spm.androidtesting.ui.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_tabcontainer.*


class TabContainerFragment : Fragment() {

    val userViewmodel: UserViewModel = UserViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "OnCreate()")
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        var binding: FragmentTabcontainerBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_tabcontainer,
                container,
                false
            )

        binding.viewmodel = userViewmodel

        lifecycle.addObserver(userViewmodel)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager()
        Log.i(TAG, "onViewCreated()")
    }

    companion object {
        val TAG = TabContainerFragment::class.java.simpleName
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TabContainerFragment().apply {
                arguments = Bundle().apply {}
            }
    }

    private fun setupViewPager() {
        val listFragment = ArrayList<Fragment>()
        val tabsArray = activity?.resources?.getStringArray(R.array.arr)

        val fragmentChat = FirstTabFragment.newInstance("", "")
        val fragmentUser = SecondTabFragment.newInstance("", "")
        val fragmentNews = ThirdTabFragment.newInstance("", "")

        listFragment.add(fragmentChat)
        listFragment.add(fragmentUser)
        listFragment.add(fragmentNews)

        val adapter = ViewpagerStateAdapter(listFragment, activity as FragmentActivity)
        viewpagerHome.adapter = adapter

        TabLayoutMediator(
            tabLayoutHome,
            viewpagerHome,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                // Styling each tab here
                tabsArray?.let { tab.setText(tabsArray[position]) }
            }).attach()
    }


    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop()")
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart()")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i(TAG, "onDestroyView()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy()")
    }
}
