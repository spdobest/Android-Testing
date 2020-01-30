package com.spm.androidtesting.account

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.spm.androidtesting.R
import com.spm.androidtesting.account.viewmodel.LoginViewModel
import com.spm.androidtesting.databinding.FragmentLoginTestBinding
import com.spm.androidtesting.utils.ExamplePreferences
import kotlinx.android.synthetic.main.fragment_login_test.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module

val loginFragmentModule = module {
    factory { LoginTestFragment() }
}

class LoginTestFragment : Fragment() {


    private val preferences: ExamplePreferences by inject()

    private val loginViewModel: LoginViewModel by viewModel()

    /* val loginViewModel: LoginViewModel by lazy {
         ViewModelProvider(this).get(LoginViewModel::class.java)
     }*/

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
        //  return inflater.inflate(R.layout.fragment_login_test, container, false)


        var binding: FragmentLoginTestBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_login_test,
                container,
                false
            )

        loginViewModel.setFragmentContext(this)
        binding.viewmodel = loginViewModel

        lifecycle.addObserver(loginViewModel)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnLogin?.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.homeTestFragment, null)
        }

        btnRegister?.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.registerTestFragment, null)
        }
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
            LoginTestFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}
