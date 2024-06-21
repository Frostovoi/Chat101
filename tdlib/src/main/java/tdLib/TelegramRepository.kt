package tdLib


import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import org.drinkless.td.libcore.telegram.TdApi
import telegramCore.core.TelegramFlow
import telegramCore.coroutines.checkAuthenticationCode
import telegramCore.coroutines.checkAuthenticationPassword
import telegramCore.coroutines.checkDatabaseEncryptionKey
import telegramCore.coroutines.logOut
import telegramCore.coroutines.setAuthenticationPhoneNumber
import telegramCore.coroutines.setTdlibParameters
import telegramCore.extensions.ChatKtx
import telegramCore.extensions.UserKtx
import telegramCore.flows.authorizationStateFlow
import javax.inject.Inject


class TelegramRepository @Inject constructor (private val context: Context) : UserKtx, ChatKtx {

    private val coroutineScope = CoroutineScope(Dispatchers.Default)
    override val api: TelegramFlow = TelegramFlow()
    
    private val authFlow = api.authorizationStateFlow()
        .onEach {
            println("state: " + it.toString())
            checkRequiredParams(it)
        }
        .map {
            when (it) {
                is TdApi.AuthorizationStateReady -> AuthState.LoggedIn
                is TdApi.AuthorizationStateWaitCode -> AuthState.EnterCode
                is TdApi.AuthorizationStateWaitPassword -> AuthState.EnterPassword
                is TdApi.AuthorizationStateWaitPhoneNumber -> AuthState.EnterPhone
                else -> AuthState.Waiting
            }
        }
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.Lazily,
            initialValue = AuthState.Initial
        )

    private suspend fun checkRequiredParams(state: TdApi.AuthorizationState?) {
        when (state) {
            is TdApi.AuthorizationStateWaitTdlibParameters -> {
                api.setTdlibParameters(TelegramCredentials.parameters.apply {
                    databaseDirectory = context.filesDir.absolutePath
                })
            }

            is TdApi.AuthorizationStateWaitEncryptionKey -> {
                api.checkDatabaseEncryptionKey(ByteArray(0))
            }

            is TdApi.AuthorizationStateReady -> {
                println("ready for use")
            }
        }
    }

    fun getAuthStateFlow(): Flow<AuthState> = authFlow

    fun checkAuthState() {
        api.attachClient()
    }

    suspend fun sendPhone(phone: String) {
        api.setAuthenticationPhoneNumber(phone, null)
    }

    suspend fun sendCode(code: String) {
        api.checkAuthenticationCode(code)
    }

    suspend fun sendPassword(password: String) {
        api.checkAuthenticationPassword(password)
    }

    suspend fun exit() {
        api.logOut()
    }
}