package com.airmineral.mymvvmsample.ui.auth

import com.airmineral.mymvvmsample.data.db.entities.User

interface AuthListener {
    fun onStarted()
    fun onSuccess(user: User)
    fun onFailure(message: String)
}