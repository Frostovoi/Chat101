//
// NOTE: THIS FILE IS AUTO-GENERATED by the "ExtensionsGenerator".kt
// See: https://github.com/tdlibx/td-ktx-generator/
//
package telegramCore.extensions

import org.drinkless.td.libcore.telegram.TdApi
import org.drinkless.td.libcore.telegram.TdApi.Call
import org.drinkless.td.libcore.telegram.TdApi.CallProblem
import org.drinkless.td.libcore.telegram.TdApi.CallProtocol
import telegramCore.core.TelegramFlow
import telegramCore.coroutines.acceptCall
import telegramCore.coroutines.discardCall
import telegramCore.coroutines.sendCallDebugInformation
import telegramCore.coroutines.sendCallRating

/**
 * Interface for access [TdApi.Call] extension functions. Can be used alongside with other extension
 * interfaces of the package. Must contain [TelegramFlow] instance field to access its functionality
 */
interface CallKtx : BaseKtx {
  /**
   * Instance of the [TelegramFlow] connecting extensions to the Telegram Client
   */
  override val api: TelegramFlow

  /**
   * Suspend function, which accepts an incoming call.
   *
   * @param protocol Description of the call protocols supported by the client.
   */
  suspend fun Call.accept(protocol: CallProtocol?) = api.acceptCall(this.id, protocol)

  /**
   * Suspend function, which discards a call.
   *
   * @param isDisconnected True, if the user was disconnected.  
   * @param duration The call duration, in seconds.  
   * @param connectionId Identifier of the connection used during the call.
   */
  suspend fun Call.discard(
    isDisconnected: Boolean,
    duration: Int,
    connectionId: Long
  ) = api.discardCall(this.id, isDisconnected, duration, connectionId)

  /**
   * Suspend function, which sends debug information for a call.
   *
   * @param debugInformation Debug information in application-specific format.
   */
  suspend fun Call.sendDebugInformation(debugInformation: String?) =
      api.sendCallDebugInformation(this.id, debugInformation)

  /**
   * Suspend function, which sends a call rating.
   *
   * @param rating Call rating; 1-5.  
   * @param comment An optional user comment if the rating is less than 5.  
   * @param problems List of the exact types of problems with the call, specified by the user.
   */
  suspend fun Call.sendRating(
    rating: Int,
    comment: String?,
    problems: Array<CallProblem>?
  ) = api.sendCallRating(this.id, rating, comment, problems)
}
