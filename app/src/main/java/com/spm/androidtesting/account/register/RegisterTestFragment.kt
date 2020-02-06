package com.spm.androidtesting.account.register

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.spm.androidtesting.R
import com.spm.androidtesting.databinding.FragmentRegisterTestBinding
import kotlinx.android.synthetic.main.fragment_register_test.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class RegisterTestFragment : Fragment() {

    private val registerViewModel: RegisterViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        loadKoinModules(registerModule)
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate")
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //  return inflater.inflate(R.layout.fragment_register_test, container, false)

        Log.i(TAG, "onCreateView")
        val binding: FragmentRegisterTestBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_register_test,
                container,
                false
            )

        binding.viewmodel = registerViewModel
        binding.fragment = this

        lifecycle.addObserver(registerViewModel)

        return binding.root

    }

    fun onLoginClicked() {
        NavHostFragment.findNavController(this).navigate(R.id.loginTestFragment, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(TAG, "onViewCreated")
        btnLogin?.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.loginTestFragment, null)
        }

        btnRegister?.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.homeTestFragment, null)
        }
    }

    companion object {
        val TAG = RegisterTestFragment::class.java.simpleName
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            RegisterTestFragment().apply {
                arguments = Bundle().apply {
//                    putString(ARG_PARAM2, param2)
                }
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
        super.onDestroyView()
        Log.i(TAG, "onDestroyView()")
    }

    override fun onDestroy() {
        unloadKoinModules(registerModule)
        super.onDestroy()
        Log.i(TAG, "onDestroy()")
    }
}
