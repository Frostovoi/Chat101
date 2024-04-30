package com.example.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.ComposeView
import com.example.core.api.mediator.AppWithFacade
import com.example.core.api.mediator.LoginMediator
import com.example.main.di.MainComponent
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var loginMediator: LoginMediator


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainComponent.create((application as AppWithFacade).getFacade()).inject(this)
        setContentView(
            ComposeView(this).apply {
                loginMediator.openLoginScreen(
                    R.id.nav_host_fragment,
                    supportFragmentManager
                )
            }
        )


    }
}