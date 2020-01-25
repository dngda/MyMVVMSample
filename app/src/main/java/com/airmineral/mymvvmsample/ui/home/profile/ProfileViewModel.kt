package com.airmineral.mymvvmsample.ui.home.profile

import androidx.lifecycle.ViewModel
import com.airmineral.mymvvmsample.data.repositories.UserRepository

class ProfileViewModel (
    repository: UserRepository
): ViewModel() {

    val user = repository.getUser()

}
