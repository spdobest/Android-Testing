package com.spm.androidtesting.account

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.spm.androidtesting.R
import kotlinx.android.synthetic.main.fragment_onboarding_test.*


class OnboardingTestFragment : Fragment() {

    // https://fabcirablog.weebly.com/blog/creating-a-never-ending-background-service-in-android
    // https://proandroiddev.com/using-livedata-flow-in-mvvm-part-i-a98fe06077a0
    // https://developer.android.com/training/constraint-layout/motionlayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_onboarding_test, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonLogin?.setOnClickListener {
            findNavController(this).navigate(R.id.loginTestFragment, null)
        }

        buttonRegister?.setOnClickListener {
            findNavController(this).navigate(R.id.registerTestFragment, null)
        }


        buttonShowHide.setOnClickListener {
            hideKeyboard(activity as FragmentActivity)
        }

    }

    fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(/*param1: String, param2: String*/) =
            OnboardingTestFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}
