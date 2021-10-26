package com.jjjjisun.test

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class KakaoApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this, "629bcbed9f698446a64088c677ef69cd")
    }
}