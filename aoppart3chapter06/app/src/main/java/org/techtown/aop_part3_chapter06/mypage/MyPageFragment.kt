package org.techtown.aop_part3_chapter06.mypage

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

import org.techtown.aop_part3_chapter06.R
import org.techtown.aop_part3_chapter06.databinding.FragmentMypageBinding

class MyPageFragment :Fragment(R.layout.fragment_mypage) {

    private var binding:FragmentMypageBinding?=null
    private val auth:FirebaseAuth by lazy {
        Firebase.auth
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentMypageBinding = FragmentMypageBinding.bind(view)
        binding=fragmentMypageBinding
        //로그인
        fragmentMypageBinding.signInOutButton.setOnClickListener {
            binding?.let{binding->
                val email =binding.emailEditText.text.toString()
                val password = binding.emailEditText.text.toString()

                if(auth.currentUser == null){
                    auth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(requireActivity()) { task ->
                            if (task.isSuccessful) {//로그인 성공
                                successSignIn()
                            } else {
                                Toast.makeText(
                                    context,
                                    "로그인에 실패했습니다. 이메일 또는 비밀번호를 확인해주세요",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                }else{
                    auth.signOut()
                    //로그아웃
                    binding.emailEditText.text.clear()
                    binding.emailEditText.isEnabled = false
                    binding.passwordEditText.text.clear()
                    binding.passwordEditText.isEnabled=false

                    binding.signInOutButton.text = "로그아웃"
                    binding.signInOutButton.isEnabled= true
                    binding.signUpButton.isEnabled=false
                }
            }
        }
        //회원가입
        fragmentMypageBinding.signUpButton.setOnClickListener {
            binding?.let{binding->
                val email =binding.emailEditText.text.toString()
                val password = binding.emailEditText.text.toString()

                auth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(requireActivity()){task->
                        if(task.isSuccessful){
                            Log.d("error",task.exception.toString())
                            Toast.makeText(context ,"회원가입에 성공했습니다. 로그인 버튼을 눌러주세요.",Toast.LENGTH_SHORT).show()
                        }
                        else{
                            Log.d("error",task.exception.toString())
                            Toast.makeText(context ,"회원가입에 실패했습니다.",Toast.LENGTH_SHORT)
                        }

                    }
            }
        }

        fragmentMypageBinding.emailEditText.addTextChangedListener {
            binding?.let{binding->
                val enable = binding.emailEditText.text.isNotEmpty() && binding.passwordEditText.text.isNotEmpty()
                    binding.signUpButton.isEnabled = enable
                    binding.signInOutButton.isEnabled = enable

            }
        }

        fragmentMypageBinding.passwordEditText.addTextChangedListener {
            binding?.let{binding->
                val enable = binding.emailEditText.text.isNotEmpty() && binding.passwordEditText.text.isNotEmpty()
                binding.signUpButton.isEnabled = enable
                binding.signInOutButton.isEnabled = enable
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if(auth.currentUser == null){ //로그인이 안되어있을때
            binding?.let { binding->
                binding.emailEditText.text.clear()
                binding.emailEditText.isEnabled = true
                binding.passwordEditText.text.clear()
                binding.passwordEditText.isEnabled=true

                binding.signInOutButton.text = "로그인"
                binding.signInOutButton.isEnabled= false
                binding.signUpButton.isEnabled=false
            }
        }else{
            binding?.let { binding->
                binding.emailEditText.setText(auth.currentUser!!.email)
                binding.passwordEditText.setText("**********")
                binding.emailEditText.isEnabled = false
                binding.passwordEditText.isEnabled=false
                binding.signInOutButton.text = "로그아웃"
                binding.signInOutButton.isEnabled= true
                binding.signUpButton.isEnabled=false
            }
        }
    }

    private fun successSignIn(){
        if(auth.currentUser == null){
            Toast.makeText(context,"로그인에 실패했습니다. 다시 시도해주세요",Toast.LENGTH_SHORT).show()
            return
        }
        binding?.emailEditText?.isEnabled = false
        binding?.passwordEditText?.isEnabled = false
        binding?.signUpButton?.isEnabled = false
        binding?.signInOutButton?.text = "로그아웃"

    }

}