package com.da62.presenter.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.da62.datasource.api.ApiService
import com.da62.presenter.base.BaseViewModel
import com.da62.usecase.LoginUseCase
import com.da62.util.SingleLiveEvent
import com.da62.util.add
import com.kakao.auth.ISessionCallback
import com.kakao.auth.Session
import com.kakao.util.exception.KakaoException
import io.reactivex.Single

class LoginViewModel(private val mUseCase: LoginUseCase) : BaseViewModel() {

    private val _mError = SingleLiveEvent<String>()

    private val _mLogin = SingleLiveEvent<Any>()

    val mError: LiveData<String> = _mError

    val mLogin: LiveData<Any> = _mLogin

    private val mKakaoCallback = object : ISessionCallback {

        override fun onSessionOpenFailed(exception: KakaoException?) {
            _mError.value = "로그인에 실패했습니다. 다시 시도해주세요."
        }

        override fun onSessionOpened() {
            // 세션은 변경될 수 있음.
            val token = Session.getCurrentSession().tokenInfo.accessToken
            login(token)
        }
    }

    init {
        Session.getCurrentSession().addCallback(mKakaoCallback)
        Session.getCurrentSession().checkAndImplicitOpen()
    }

    private fun login(accessToken: String) {
        compositeDisposable add
                mUseCase.login(accessToken)
                    .subscribe({
                        it
                    }, {
                        it.printStackTrace()
                    })
    }

    override fun onCleared() {
        super.onCleared()
        Session.getCurrentSession().removeCallback(mKakaoCallback)
    }
}