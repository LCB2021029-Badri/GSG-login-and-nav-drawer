package com.example.get_set_go

import android.content.Intent
import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.get_set_go.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBinding.inflate(inflater)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.signupBtnL.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment2_to_signupFragment2)
        }

        binding.loginBtnL.setOnClickListener {
            val email = binding.emailInputL.text.toString()
            val pass = binding.passwordInputL.text.toString()
            if(email.isNotEmpty() && pass.isNotEmpty()){
                    firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener {
                        if(it.isSuccessful){ // if authentication is successful then we navigate to the next intent/fragment ( I AM NAVIGATING USING INTENT BECAUSE I WANT TO USE NAVIGATION BAR FROM THE NEXT PAGE)
//                            findNavController().navigate(R.id.action_loginFragment2_to_homepageFragment)
                            val intent = Intent(this@LoginFragment.requireContext(),SecondActivity::class.java)
                            startActivity(intent)
                        }
                        else{
                            Toast.makeText(getActivity(),it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            else{
                Toast.makeText(getActivity(),"empty fields not allowed !", Toast.LENGTH_SHORT).show()
            }
        }
        binding.forgotPasswordBtnL.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment2_to_forgotPasswordFragment)
        }

        return binding.root
    }
}