package com.example.login

import androidx.fragment.app.FragmentManager
import com.example.core.api.mediator.LoginMediator
import javax.inject.Inject

class LoginMediatorImpl @Inject constructor() : LoginMediator {
    override fun openLoginScreen(containerId: Int, fragmentManager: FragmentManager) {
        fragmentManager.beginTransaction()
            .add(containerId, LoginFragment.newInstance())
            .commit()
    }
}