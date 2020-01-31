package com.spm.androidtesting.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.spm.androidtesting.R
import com.spm.androidtesting.account.viewmodel.RegisterViewModel
import com.spm.androidtesting.databinding.FragmentRegisterTestBinding
import kotlinx.android.synthetic.main.fragment_register_test.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module

val registerFragmentModule = module {
    factory { RegisterTestFragment() }
}

class RegisterTestFragment : Fragment() {

    private val registerViewModel: RegisterViewModel by viewModel()

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
        //  return inflater.inflate(R.layout.fragment_register_test, container, false)


        var binding: FragmentRegisterTestBinding =
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

        btnLogin?.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.loginTestFragment, null)
        }

        btnRegister?.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.homeTestFragment, null)
        }
    }


    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            RegisterTestFragment().apply {
                arguments = Bundle().apply {
                    //                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
