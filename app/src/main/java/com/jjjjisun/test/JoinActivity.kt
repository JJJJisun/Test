package com.jjjjisun.test

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import com.jjjjisun.test.databinding.ActivityJoinBinding
import java.util.regex.Pattern

class JoinActivity : AppCompatActivity(), View.OnClickListener {

    val binding by lazy { ActivityJoinBinding.inflate(layoutInflater) }
    val emailValidation =
        "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
    val passwdValidation = "^(?=.*[a-zA-Z0-9])(?=.*[a-zA-Z!@#\$%^&*])(?=.*[0-9!@#\$%^&*]).{8,15}\$"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.joinName.setOnClickListener(this)
        binding.joinId.setOnClickListener(this)
        binding.joinPw.setOnClickListener(this)
        binding.joinCheckPw.setOnClickListener(this)

        binding.joinBtn.setOnClickListener {
            if (binding.joinName.text.toString() == "") {
                Toast.makeText(this, "이름을 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else if (binding.joinPw.text.toString() == "") {
                Toast.makeText(this, "패스워드를 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else if (binding.joinId.text.toString() == "") {
                Toast.makeText(this, "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else if (binding.joinCheckPw.text.toString() == "") {
                Toast.makeText(this, "패스워드를 확인해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                finish()
            }
        }


        binding.joinId.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                7
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkEmail()
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
        binding.joinPw.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkPasswd()
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

        binding.joinCheckPw.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.joinPw.text.toString() == binding.joinCheckPw.text.toString()) {
                    binding.errorCheckPw.visibility = View.GONE
                } else {
                    binding.errorCheckPw.visibility = View.VISIBLE
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.joinName.id -> {
                binding.joinName.setBackgroundResource(R.drawable.join_click)
                binding.joinId.setBackgroundResource(R.drawable.join_nonclick)
                binding.joinPw.setBackgroundResource(R.drawable.join_nonclick)
                binding.joinCheckPw.setBackgroundResource(R.drawable.join_nonclick)
            }
            binding.joinId.id -> {
                binding.joinName.setBackgroundResource(R.drawable.join_nonclick)
                binding.joinId.setBackgroundResource(R.drawable.join_click)
                binding.joinPw.setBackgroundResource(R.drawable.join_nonclick)
                binding.joinCheckPw.setBackgroundResource(R.drawable.join_nonclick)
            }
            binding.joinPw.id -> {
                binding.joinName.setBackgroundResource(R.drawable.join_nonclick)
                binding.joinId.setBackgroundResource(R.drawable.join_nonclick)
                binding.joinPw.setBackgroundResource(R.drawable.join_click)
                binding.joinCheckPw.setBackgroundResource(R.drawable.join_nonclick)
            }

            binding.joinCheckPw.id -> {
                binding.joinName.setBackgroundResource(R.drawable.join_nonclick)
                binding.joinId.setBackgroundResource(R.drawable.join_nonclick)
                binding.joinPw.setBackgroundResource(R.drawable.join_nonclick)
                binding.joinCheckPw.setBackgroundResource(R.drawable.join_click)
            }
        }
    }

    fun checkEmail(): Boolean {
        var email = binding.joinId.text.toString().trim() // trim: 문자열의 양쪽 공백을 없애주는 것
        val pattern = Pattern.matches(emailValidation, email)
        if (pattern) {
            binding.joinId.setTextColor(R.color.black.toInt())
            binding.errorEmail.visibility = View.GONE
            return true
        } else {
            binding.joinId.setTextColor(-65536)
            binding.errorEmail.visibility = View.VISIBLE
            return false
        }
    }


    fun checkPasswd(): Boolean {
        var passwd = binding.joinPw.text.toString().trim()
        val pattern = Pattern.matches(passwdValidation, passwd)
        if (pattern) {
            binding.errorPw.visibility = View.GONE
            return true
        } else {
            binding.errorPw.visibility = View.VISIBLE
            return false
        }
    }
}