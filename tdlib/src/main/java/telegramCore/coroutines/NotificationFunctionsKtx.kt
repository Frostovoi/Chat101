//
// NOTE: THIS FILE IS AUTO-GENERATED by the "TdApiKtxGenerator".kt
// See: https://github.com/tdlibx/td-ktx-generator/
//
package telegramCore.coroutines

import org.drinkless.td.libcore.telegram.TdApi
import org.drinkless.td.libcore.telegram.TdApi.NotificationSettingsScope
import org.drinkless.td.libcore.telegram.TdApi.ScopeNotificationSettings
import telegramCore.core.TelegramFlow

/**
 * Suspend function, which returns the notification settings for chats of a given type.
 *
 * @param scope Types of chats for which to return the notification settings information.
 *
 * @return [ScopeNotificationSettings] Contains information about notification settings for several
 * chats.
 */
suspend fun TelegramFlow.getScopeNotificationSettings(scope: NotificationSettingsScope?):
    ScopeNotificationSettings = this.sendFunctionAsync(TdApi.GetScopeNotificationSettings(scope))

/**
 * Suspend function, which handles a push notification. Returns error with code 406 if the push
 * notification is not supported and connection to the server is required to fetch new data. Can be
 * called before authorization.
 *
 * @param payload JSON-encoded push notification payload with all fields sent by the server, and
 * &quot;google.sentTime&quot; and &quot;google.notification.sound&quot; fields added.
 */
suspend fun TelegramFlow.processPushNotification(payload: String?) =
    this.sendFunctionLaunch(TdApi.ProcessPushNotification(payload))

/**
 * Suspend function, which removes an active notification from notification list. Needs to be called
 * only if the notification is removed by the current user.
 *
 * @param notificationGroupId Identifier of notification group to which the notification belongs.  
 * @param notificationId Identifier of removed notification.
 */
suspend fun TelegramFlow.removeNotification(notificationGroupId: Int, notificationId: Int) =
    this.sendFunctionLaunch(TdApi.RemoveNotification(notificationGroupId, notificationId))

/**
 * Suspend function, which removes a group of active notifications. Needs to be called only if the
 * notification group is removed by the current user.
 *
 * @param notificationGroupId Notification group identifier.  
 * @param maxNotificationId The maximum identifier of removed notifications.
 */
suspend fun TelegramFlow.removeNotificationGroup(notificationGroupId: Int, maxNotificationId: Int) =
    this.sendFunctionLaunch(TdApi.RemoveNotificationGroup(notificationGroupId, maxNotificationId))

/**
 * Suspend function, which resets all notification settings to their default values. By default, all
 * chats are unmuted, the sound is set to &quot;default&quot; and message previews are shown.
 */
suspend fun TelegramFlow.resetAllNotificationSettings() =
    this.sendFunctionLaunch(TdApi.ResetAllNotificationSettings())

/**
 * Suspend function, which changes notification settings for chats of a given type.
 *
 * @param scope Types of chats for which to change the notification settings.  
 * @param notificationSettings The new notification settings for the given scope.
 */
suspend fun TelegramFlow.setScopeNotificationSettings(scope: NotificationSettingsScope?,
                                                      notificationSettings: ScopeNotificationSettings?) =
    this.sendFunctionLaunch(TdApi.SetScopeNotificationSettings(scope, notificationSettings))
