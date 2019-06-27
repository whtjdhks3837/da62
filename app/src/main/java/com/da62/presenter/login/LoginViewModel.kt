package com.da62.presenter.login

import androidx.lifecycle.LiveData
import com.da62.presenter.base.BaseViewModel
import com.da62.usecase.LoginUseCase
import com.da62.util.SingleLiveEvent
import com.da62.util.add
import com.kakao.auth.ISessionCallback
import com.kakao.auth.Session
import com.kakao.util.exception.KakaoException

class LoginViewModel(private val useCase: LoginUseCase) : BaseViewModel() {

    private val _error = SingleLiveEvent<String>()

    private val _login = SingleLiveEvent<Any>()

    val error: LiveData<String> = _error

    val login: LiveData<Any> = _login

    private val kakaoCallback = object : ISessionCallback {

        override fun onSessionOpenFailed(exception: KakaoException?) {
            _error.value = "로그인에 실패했습니다. 다시 시도해주세요."
        }

        override fun onSessionOpened() {
            // 세션은 변경될 수 있음.
            val token = Session.getCurrentSession().tokenInfo.accessToken
            login(token)
        }
    }

    init {
        Session.getCurrentSession().addCallback(kakaoCallback)
        Session.getCurrentSession().checkAndImplicitOpen()
    }

    private fun login(accessToken: String) {
        compositeDisposable add
                useCase.login(accessToken)
                    .subscribe({
                        it
                    }, {
                        it.printStackTrace()
                    })
    }

    override fun onCleared() {
        super.onCleared()
        Session.getCurrentSession().removeCallback(kakaoCallback)
    }
}