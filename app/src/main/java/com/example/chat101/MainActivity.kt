package com.example.chat101

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.core.api.mediator.AppWithFacade
import com.example.main.Navigation
import kotlinx.coroutines.launch
import tdLib.TelegramRepository
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var repository: TelegramRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainComponent.create((application as AppWithFacade).getFacade()).inject(this)
        setContent{
            Navigation()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycleScope.launch {
            repository.exit()
        }
    }

}