package com.jjjjisun.test

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.jjjjisun.test.databinding.ActivityLoginBinding
import com.nhn.android.naverlogin.OAuthLogin
import com.nhn.android.naverlogin.OAuthLogin.mOAuthLoginHandler
import com.nhn.android.naverlogin.OAuthLoginHandler

class LoginActivity : AppCompatActivity() {

    val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }

    lateinit var mOAuthLoginInstance: OAuthLogin
    lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val naver_client_id = "OKwTIBNeS0ibqD_Vmjl8"
        val naver_client_secret = "SdGIoJegIt"
        val naver_client_name = "team_test"

        mContext = this
        mOAuthLoginInstance = OAuthLogin.getInstance()
        mOAuthLoginInstance.init(mContext, naver_client_id, naver_client_secret, naver_client_name)

        //로그인 버튼 클릭
        binding.btnNaverLogin.setOAuthLoginHandler(mOAuthLoginHandler)

        binding.btnLogin.setOnClickListener {
            startActivity(Intent(this, JoinActivity::class.java))
        }
    }

    val mOAuthLoginHandler: OAuthLoginHandler = object : OAuthLoginHandler() {
        override fun run(success: Boolean) {
            if (success) {
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            } else {
                val errorCode: String = mOAuthLoginInstance.getLastErrorCode(mContext).code
                val errorDesc = mOAuthLoginInstance.getLastErrorDesc(mContext)

                Toast.makeText(
                    baseContext,
                    "errorCode:" + errorCode + ", errorDesc:" + errorDesc,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}