package com.example.get_set_go

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.get_set_go.databinding.FragmentForgotPasswordBinding
import com.example.get_set_go.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordFragment : Fragment() {

    lateinit var binding: FragmentForgotPasswordBinding
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentForgotPasswordBinding.inflate(inflater)
        firebaseAuth = FirebaseAuth.getInstance()


        binding.confirmF.setOnClickListener {
            val email = binding.emailInputF.text.toString()
            if(email.isEmpty()){
                Toast.makeText(this@ForgotPasswordFragment.requireContext(),"Please enter E-mail address",
                    Toast.LENGTH_SHORT).show()
            }
            else{
                FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener{
                    if(it.isSuccessful){
                        Toast.makeText(this@ForgotPasswordFragment.requireContext(),"E-mail sent successfully to reset your password",
                            Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Toast.makeText(this@ForgotPasswordFragment.requireContext(),it.exception!!.message.toString(),
                            Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }

        return binding.root
    }
}