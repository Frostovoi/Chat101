package tdLib

import org.drinkless.td.libcore.telegram.TdApi


//Replace with your personal telegram credentials
object TelegramCredentials {
    val parameters = TdApi.TdlibParameters().apply {
        useMessageDatabase = false
        useSecretChats = false
        useFileDatabase = true
        systemLanguageCode = "en"
        deviceModel = "Android"
        systemVersion = "Example"
        applicationVersion = "1.0"
        enableStorageOptimizer = true
        apiId = 20935635
        apiHash = "3de4252c350ab93c45c8d6ad2da468ca"
        databaseDirectory = "/"
    }
}