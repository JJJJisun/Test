package com.jjjjisun.test

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.jjjjisun.test.databinding.ActivityJoinBinding

class JoinActivity : AppCompatActivity(), View.OnClickListener {

	val binding by lazy { ActivityJoinBinding.inflate(layoutInflater) }

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)

		runOnUiThread {
			binding.joinName.setOnClickListener(this)
			binding.joinId.setOnClickListener(this)
			binding.joinPw.setOnClickListener(this)
			binding.joinCheckPw.setOnClickListener(this)
		}

		binding.signupBtn.setOnClickListener {
			finish()
		}

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
}