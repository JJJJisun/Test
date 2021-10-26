package com.jjjjisun.test

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.jjjjisun.test.databinding.ActivityLoginBinding
import com.kakao.sdk.auth.LoginClient
import com.kakao.sdk.auth.model.OAuthToken
import com.nhn.android.naverlogin.OAuthLogin
import com.nhn.android.naverlogin.OAuthLoginHandler
import com.kakao.sdk.common.model.AuthErrorCause.*

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

        //네이버 로그인 버튼 클릭
        binding.btnNaverLogin.setOAuthLoginHandler(mOAuthLoginHandler)

        //카카로 로그인 버튼 클릭
        binding.btnKakaoLogin.setOnClickListener {
            if(LoginClient.instance.isKakaoTalkLoginAvailable(this)){
                LoginClient.instance.loginWithKakaoTalk(this, callback = callback)
            }else{
                LoginClient.instance.loginWithKakaoAccount(this, callback = callback)
            }
        }

        //회원가입 클릭
        binding.tvLogin.setOnClickListener {
            startActivity(Intent(this, JoinActivity::class.java))
        }

    }

    //네이버 로그인
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

    //카카오 로그인
    val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            when {
                error.toString() == AccessDenied.toString() -> {
                    Toast.makeText(this, "접근이 거부 됨(동의 취소)", Toast.LENGTH_SHORT).show()
                }
                error.toString() == InvalidClient.toString() -> {
                    Toast.makeText(this, "유효하지 않은 앱", Toast.LENGTH_SHORT).show()
                }
                error.toString() == InvalidGrant.toString() -> {
                    Toast.makeText(this, "인증 수단이 유효하지 않아 인증할 수 없는 상태", Toast.LENGTH_SHORT).show()
                }
                error.toString() == InvalidRequest.toString() -> {
                    Toast.makeText(this, "요청 파라미터 오류", Toast.LENGTH_SHORT).show()
                }
                error.toString() == InvalidScope.toString() -> {
                    Toast.makeText(this, "유효하지 않은 scope ID", Toast.LENGTH_SHORT).show()
                }
                error.toString() == Misconfigured.toString() -> {
                    Toast.makeText(this, "설정이 올바르지 않음(android key hash)", Toast.LENGTH_SHORT).show()
                }
                error.toString() == ServerError.toString() -> {
                    Toast.makeText(this, "서버 내부 에러", Toast.LENGTH_SHORT).show()
                }
                error.toString() == Unauthorized.toString() -> {
                    Toast.makeText(this, "앱이 요청 권한이 없음", Toast.LENGTH_SHORT).show()
                }
                else -> { // Unknown
                    Toast.makeText(this, "기타 에러", Toast.LENGTH_SHORT).show()
                }
            }
        }
        else if (token != null) {
            Toast.makeText(this, "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}