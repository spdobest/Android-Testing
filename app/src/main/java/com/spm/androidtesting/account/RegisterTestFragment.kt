package com.spm.androidtesting.account

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.spm.androidtesting.R
import com.spm.androidtesting.account.viewmodel.RegisterViewModel
import com.spm.androidtesting.databinding.FragmentRegisterTestBinding
import kotlinx.android.synthetic.main.fragment_register_test.*

class RegisterTestFragment : Fragment() {

    val registerViewModel: RegisterViewModel by lazy {
        ViewModelProvider(this).get(RegisterViewModel::class.java)
    }

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
                R.layout.fragment_login_test,
                container,
                false
            )

        registerViewModel.setFragmentContext(this)
        binding.viewmodel = registerViewModel

        lifecycle.addObserver(registerViewModel)

        return binding.root

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

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onDetach() {
        super.onDetach()

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
