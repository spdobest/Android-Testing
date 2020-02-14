package com.spm.androidtesting.ui.fragment


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.spm.androidtesting.R
import com.spm.androidtesting.databinding.FragmentTabBinding
import com.spm.androidtesting.ui.NextActivity
import com.spm.androidtesting.ui.viewmodel.TabViewModel
import com.spm.androidtesting.utils.ExamplePreferences
import kotlinx.android.synthetic.main.fragment_tab.*
import org.koin.android.ext.android.inject
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.module


class SecondTabFragment : Fragment() {
    val examplePreferences: ExamplePreferences by inject()
  //  val userViewmodel: TabViewModel = TabViewModel()

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

        Log.i(TAG, "onCreateView")
        var binding: FragmentTabBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_tab,
                container,
                false
            )

//        binding.viewmodel = userViewmodel
//
//        lifecycle.addObserver(userViewmodel)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvMessage.text = "Second TAB"
        tvMessage.setOnClickListener {
            examplePreferences.storeShouldShowFragment(false)
            activity?.startActivity(Intent(activity, NextActivity::class.java))
        }
        Log.i(TAG, "onViewCreated()")
    }

    companion object {
        val TAG = SecondTabFragment::class.java.simpleName
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SecondTabFragment().apply {
                arguments = Bundle().apply {}
            }
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
     //   unloadKoinModules(secondTabModule)
        super.onDestroyView()
        Log.i(TAG, "onDestroyView()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy()")
    }
}
