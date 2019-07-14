package com.da62.datasource.local

import com.da62.model.User

interface LoginLocalDataSource {

    fun saveUser(user: User)
}

class LoginLocalDataSourceImpl : LoginLocalDataSource {

    companion object {
        val localMap = mutableMapOf<String, Any>()
    }

    override fun saveUser(user: User) {
        localMap["user"] = user
    }
}