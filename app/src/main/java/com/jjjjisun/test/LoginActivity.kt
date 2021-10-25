package com.jjjjisun.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jjjjisun.test.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

	val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)

		binding.join.setOnClickListener {
			startActivity(Intent(this, JoinActivity::class.java))
		}
	}
}