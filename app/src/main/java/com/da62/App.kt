package com.da62

import android.app.Application
import android.util.Log
import com.da62.datasource.di.appModules
import com.google.firebase.iid.FirebaseInstanceId
import com.kakao.auth.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        KakaoSDK.init(kakaoAdapter)
        startKoin {
            androidContext(this@App)

            modules(appModules)
        }
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener  { task ->
                if (task.isSuccessful) {
                    task.result?.run {
                        Log.i("TAGTAG", "$token")
                    }
                }
            }
    }

    private val kakaoAdapter = object : KakaoAdapter() {
        override fun getApplicationConfig() = IApplicationConfig { applicationContext }

        override fun getSessionConfig(): ISessionConfig {
            return object : ISessionConfig {
                override fun isSaveFormData() = true

                override fun getAuthTypes() = Array(1) { AuthType.KAKAO_LOGIN_ALL }

                override fun isSecureMode() = false

                override fun getApprovalType(): ApprovalType? = ApprovalType.INDIVIDUAL

                override fun isUsingWebviewTimer() = false
            }
        }
    }
}