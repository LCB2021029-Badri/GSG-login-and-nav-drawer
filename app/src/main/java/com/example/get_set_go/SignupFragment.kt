package com.example.get_set_go

import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.get_set_go.databinding.FragmentLoginBinding
import com.example.get_set_go.databinding.FragmentSignupBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class SignupFragment : Fragment() {

    lateinit var binding: FragmentSignupBinding
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        val view = inflater.inflate(R.layout.fragment_signup,container,false)

        binding = FragmentSignupBinding.inflate(inflater)   // binding instance
        firebaseAuth = FirebaseAuth.getInstance()   // firebase instance

        binding.loginBtnS.setOnClickListener {  // fragment change
            findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
        }

        binding.confirmBtnS.setOnClickListener {
            val email = binding.emailInputS.text.toString()
            val pass = binding.passwordInputS.text.toString()
            val confirmPass = binding.reenterPasswordInputS.text.toString()
            if(email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()){
                if(pass == confirmPass){
                    firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener {
                        if(it.isSuccessful){
                            findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
                        }else{
                            Toast.makeText(getActivity(),it.exception.toString(),Toast.LENGTH_SHORT).show()
                        }
                    }
                }else{
                    Toast.makeText(getActivity(),"password is not matching !",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(getActivity(),"empty fields not allowed !",Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }
}