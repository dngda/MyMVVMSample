package com.airmineral.mymvvmsample.data.network.responses

import com.airmineral.mymvvmsample.data.db.entities.User

data class AuthResponse (
    val isSuccessful : Boolean?,
    val message : String?,
    val user : User?
)