package com.example.conversation

import androidx.lifecycle.ViewModel
import com.example.network.data.DataState
import com.example.network.data.MessageService
import com.example.network.data.UIComponent
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject


class ConversationViewModel(
    channelName: String,
    channelMembers: Int
) : ViewModel(), ContainerHost<ConversationState, UIComponent> {

    @Inject
    lateinit var messageService: MessageService

    override val container: Container<ConversationState, UIComponent> = container(
        ConversationState(
            channelName = channelName,
            channelMembers = channelMembers
        )
    )


    fun getAllMessages() {
        intent {
            when(val messages = messageService.getAllMessages()){
                is DataState.Success -> {
                    reduce {
                        state.copy(messages = messages.data)
                    }
                }
                is DataState.Error -> {
                    when (messages.uiComponent) {
                        is UIComponent.Toast -> {
                            postSideEffect(
                                UIComponent.Toast((messages.uiComponent as UIComponent.Toast).text)
                            )
                        }
                    }
                }
            }
        }
    }

}