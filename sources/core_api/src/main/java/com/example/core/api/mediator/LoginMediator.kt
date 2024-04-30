package com.example.core.api.mediator

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager

interface LoginMediator {

    fun openLoginScreen(@IdRes containerId: Int, fragmentManager: FragmentManager)

}