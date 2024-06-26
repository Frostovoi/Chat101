//
// NOTE: THIS FILE IS AUTO-GENERATED by the "ExtensionsGenerator".kt
// See: https://github.com/tdlibx/td-ktx-generator/
//
package telegramCore.extensions

import org.drinkless.td.libcore.telegram.TdApi
import org.drinkless.td.libcore.telegram.TdApi.CallbackQueryPayload
import org.drinkless.td.libcore.telegram.TdApi.FormattedText
import org.drinkless.td.libcore.telegram.TdApi.InputMessageContent
import org.drinkless.td.libcore.telegram.TdApi.Location
import org.drinkless.td.libcore.telegram.TdApi.Message
import org.drinkless.td.libcore.telegram.TdApi.MessageSchedulingState
import org.drinkless.td.libcore.telegram.TdApi.OrderInfo
import org.drinkless.td.libcore.telegram.TdApi.ReplyMarkup
import telegramCore.core.TelegramFlow
import telegramCore.coroutines.deleteChatReplyMarkup
import telegramCore.coroutines.editMessageCaption
import telegramCore.coroutines.editMessageLiveLocation
import telegramCore.coroutines.editMessageMedia
import telegramCore.coroutines.editMessageReplyMarkup
import telegramCore.coroutines.editMessageSchedulingState
import telegramCore.coroutines.editMessageText
import telegramCore.coroutines.getCallbackQueryAnswer
import telegramCore.coroutines.getGameHighScores
import telegramCore.coroutines.getLoginUrl
import telegramCore.coroutines.getLoginUrlInfo
import telegramCore.coroutines.getMessage
import telegramCore.coroutines.getMessageLink
import telegramCore.coroutines.getMessageLocally
import telegramCore.coroutines.getPaymentForm
import telegramCore.coroutines.getPaymentReceipt
import telegramCore.coroutines.getPollVoters
import telegramCore.coroutines.getRepliedMessage
import telegramCore.coroutines.openMessageContent
import telegramCore.coroutines.pinChatMessage
import telegramCore.coroutines.setGameScore
import telegramCore.coroutines.setPollAnswer
import telegramCore.coroutines.stopPoll
import telegramCore.coroutines.validateOrderInfo

/**
 * Interface for access [TdApi.Message] extension functions. Can be used alongside with other
 * extension interfaces of the package. Must contain [TelegramFlow] instance field to access its
 * functionality
 */
interface MessageKtx : BaseKtx {
  /**
   * Instance of the [TelegramFlow] connecting extensions to the Telegram Client
   */
  override val api: TelegramFlow

  /**
   * Suspend function, which deletes the default reply markup from a chat. Must be called after a
   * one-time keyboard or a ForceReply reply markup has been used. UpdateChatReplyMarkup will be sent
   * if the reply markup will be changed.
   *
   * @param chatId Chat identifier.  
   */
  suspend fun Message.deleteChatReplyMarkup(chatId: Long) = api.deleteChatReplyMarkup(chatId,
      this.id)

  /**
   * Suspend function, which edits the message content caption. Returns the edited message after the
   * edit is completed on the server side.
   *
   * @param chatId The chat the message belongs to.  
   * @param replyMarkup The new message reply markup; for bots only.  
   * @param caption New message content caption; 0-GetOption(&quot;message_caption_length_max&quot;)
   * characters.
   *
   * @return [TdApi.Message] Describes a message.
   */
  suspend fun Message.editCaption(
    chatId: Long,
    replyMarkup: ReplyMarkup?,
    caption: FormattedText?
  ) = api.editMessageCaption(chatId, this.id, replyMarkup, caption)

  /**
   * Suspend function, which edits the message content of a live location. Messages can be edited
   * for a limited period of time specified in the live location. Returns the edited message after the
   * edit is completed on the server side.
   *
   * @param chatId The chat the message belongs to.  
   * @param replyMarkup The new message reply markup; for bots only.  
   * @param location New location content of the message; may be null. Pass null to stop sharing the
   * live location.
   *
   * @return [TdApi.Message] Describes a message.
   */
  suspend fun Message.editLiveLocation(
    chatId: Long,
    replyMarkup: ReplyMarkup?,
    location: Location? = null
  ) = api.editMessageLiveLocation(chatId, this.id, replyMarkup, location)

  /**
   * Suspend function, which edits the content of a message with an animation, an audio, a document,
   * a photo or a video. The media in the message can't be replaced if the message was set to
   * self-destruct. Media can't be replaced by self-destructing media. Media in an album can be edited
   * only to contain a photo or a video. Returns the edited message after the edit is completed on the
   * server side.
   *
   * @param chatId The chat the message belongs to.  
   * @param replyMarkup The new message reply markup; for bots only.  
   * @param inputMessageContent New content of the message. Must be one of the following types:
   * InputMessageAnimation, InputMessageAudio, InputMessageDocument, InputMessagePhoto or
   * InputMessageVideo.
   *
   * @return [TdApi.Message] Describes a message.
   */
  suspend fun Message.editMedia(
    chatId: Long,
    replyMarkup: ReplyMarkup?,
    inputMessageContent: InputMessageContent?
  ) = api.editMessageMedia(chatId, this.id, replyMarkup, inputMessageContent)

  /**
   * Suspend function, which edits the message reply markup; for bots only. Returns the edited
   * message after the edit is completed on the server side.
   *
   * @param chatId The chat the message belongs to.  
   * @param replyMarkup The new message reply markup.
   *
   * @return [TdApi.Message] Describes a message.
   */
  suspend fun Message.editReplyMarkup(chatId: Long, replyMarkup: ReplyMarkup?) =
      api.editMessageReplyMarkup(chatId, this.id, replyMarkup)

  /**
   * Suspend function, which edits the time when a scheduled message will be sent. Scheduling state
   * of all messages in the same album or forwarded together with the message will be also changed.
   *
   * @param chatId The chat the message belongs to.  
   * @param schedulingState The new message scheduling state. Pass null to send the message
   * immediately.
   */
  suspend fun Message.editSchedulingState(chatId: Long, schedulingState: MessageSchedulingState?) =
      api.editMessageSchedulingState(chatId, this.id, schedulingState)

  /**
   * Suspend function, which edits the text of a message (or a text of a game message). Returns the
   * edited message after the edit is completed on the server side.
   *
   * @param chatId The chat the message belongs to.  
   * @param replyMarkup The new message reply markup; for bots only.  
   * @param inputMessageContent New text content of the message. Should be of type InputMessageText.
   *
   * @return [TdApi.Message] Describes a message.
   */
  suspend fun Message.editText(
    chatId: Long,
    replyMarkup: ReplyMarkup?,
    inputMessageContent: InputMessageContent?
  ) = api.editMessageText(chatId, this.id, replyMarkup, inputMessageContent)

  /**
   * Suspend function, which sends a callback query to a bot and returns an answer. Returns an error
   * with code 502 if the bot fails to answer the query before the query timeout expires.
   *
   * @param chatId Identifier of the chat with the message.  
   * @param payload Query payload.
   *
   * @return [TdApi.CallbackQueryAnswer] Contains a bot's answer to a callback query.
   */
  suspend fun Message.getCallbackQueryAnswer(chatId: Long, payload: CallbackQueryPayload?) =
      api.getCallbackQueryAnswer(chatId, this.id, payload)

  /**
   * Suspend function, which returns the high scores for a game and some part of the high score
   * table in the range of the specified user; for bots only.
   *
   * @param chatId The chat that contains the message with the game.  
   * @param userId User identifier.
   *
   * @return [TdApi.GameHighScores] Contains a list of game high scores.
   */
  suspend fun Message.getGameHighScores(chatId: Long, userId: Long) = api.getGameHighScores(chatId,
      this.id, userId)

  /**
   * Suspend function, which returns an HTTP URL which can be used to automatically authorize the
   * user on a website after clicking an inline button of type inlineKeyboardButtonTypeLoginUrl. Use
   * the method getLoginUrlInfo to find whether a prior user confirmation is needed. If an error is
   * returned, then the button must be handled as an ordinary URL button.
   *
   * @param chatId Chat identifier of the message with the button.  
   * @param buttonId Button identifier.  
   * @param allowWriteAccess True, if the user allowed the bot to send them messages.
   *
   * @return [TdApi.HttpUrl] Contains an HTTP URL.
   */
  suspend fun Message.getLoginUrl(
    chatId: Long,
    buttonId: Long,
    allowWriteAccess: Boolean
  ) = api.getLoginUrl(chatId, this.id, buttonId, allowWriteAccess)

  /**
   * Suspend function, which returns information about a button of type
   * inlineKeyboardButtonTypeLoginUrl. The method needs to be called when the user presses the button.
   *
   * @param chatId Chat identifier of the message with the button.  
   * @param buttonId Button identifier.
   *
   * @return [TdApi.LoginUrlInfo] This class is an abstract base class.
   */
  suspend fun Message.getLoginUrlInfo(chatId: Long, buttonId: Long) = api.getLoginUrlInfo(chatId,
      this.id, buttonId)

  /**
   * Suspend function, which returns information about a message.
   *
   * @param chatId Identifier of the chat the message belongs to.  
   *
   * @return [TdApi.Message] Describes a message.
   */
  suspend fun Message.get(chatId: Long) = api.getMessage(chatId, this.id)

  /**
   * Suspend function, which returns a private HTTPS link to a message in a chat. Available only for
   * already sent messages in supergroups and channels. The link will work only for members of the
   * chat.
   *
   * @param chatId Identifier of the chat to which the message belongs.  
   *
   * @return [TdApi.HttpUrl] Contains an HTTP URL.
   */
  suspend fun Message.getLink(chatId: Long) = api.getMessageLink(chatId, this.id)

  /**
   * Suspend function, which returns information about a message, if it is available locally without
   * sending network request. This is an offline request.
   *
   * @param chatId Identifier of the chat the message belongs to.  
   *
   * @return [TdApi.Message] Describes a message.
   */
  suspend fun Message.getLocally(chatId: Long) = api.getMessageLocally(chatId, this.id)

  /**
   * Suspend function, which returns an invoice payment form. This method should be called when the
   * user presses inlineKeyboardButtonBuy.
   *
   * @param chatId Chat identifier of the Invoice message.  
   *
   * @return [TdApi.PaymentForm] Contains information about an invoice payment form.
   */
  suspend fun Message.getPaymentForm(chatId: Long) = api.getPaymentForm(chatId, this.id)

  /**
   * Suspend function, which returns information about a successful payment.
   *
   * @param chatId Chat identifier of the PaymentSuccessful message.  
   *
   * @return [TdApi.PaymentReceipt] Contains information about a successful payment.
   */
  suspend fun Message.getPaymentReceipt(chatId: Long) = api.getPaymentReceipt(chatId, this.id)

  /**
   * Suspend function, which returns users voted for the specified option in a non-anonymous polls.
   * For the optimal performance the number of returned users is chosen by the library.
   *
   * @param chatId Identifier of the chat to which the poll belongs.  
   * @param optionId 0-based identifier of the answer option.  
   * @param offset Number of users to skip in the result; must be non-negative.  
   * @param limit The maximum number of users to be returned; must be positive and can't be greater
   * than 50. Fewer users may be returned than specified by the limit, even if the end of the voter
   * list has not been reached.
   *
   * @return [TdApi.Users] Represents a list of users.
   */
  suspend fun Message.getPollVoters(
    chatId: Long,
    optionId: Int,
    offset: Int,
    limit: Int
  ) = api.getPollVoters(chatId, this.id, optionId, offset, limit)

  /**
   * Suspend function, which returns a public HTTPS link to a message. Available only for messages
   * in supergroups and channels with a username.
   *
   * @param chatId Identifier of the chat to which the message belongs.  
   * @param forAlbum Pass true if a link for a whole media album should be returned.
   *
   * @return [TdApi.PublicMessageLink] Contains a public HTTPS link to a message in a supergroup or
   * channel with a username.
   */
  //suspend fun Message.getPublicLink(chatId: Long, forAlbum: Boolean) =
  //    api.getPublicMessageLink(chatId, this.id, forAlbum)

  /**
   * Suspend function, which returns information about a message that is replied by given message.
   *
   * @param chatId Identifier of the chat the message belongs to.  
   *
   * @return [TdApi.Message] Describes a message.
   */
  suspend fun Message.getReplied(chatId: Long) = api.getRepliedMessage(chatId, this.id)

  /**
   * Suspend function, which informs TDLib that the message content has been opened (e.g., the user
   * has opened a photo, video, document, location or venue, or has listened to an audio file or voice
   * note message). An updateMessageContentOpened update will be generated if something has changed.
   *
   * @param chatId Chat identifier of the message.  
   */
  suspend fun Message.openContent(chatId: Long) = api.openMessageContent(chatId, this.id)

  /**
   * Suspend function, which pins a message in a chat; requires canPinMessages rights.
   *
   * @param chatId Identifier of the chat.  
   * @param disableNotification True, if there should be no notification about the pinned message.
   */
  suspend fun Message.pinChat(chatId: Long, disableNotification: Boolean) =
      api.pinChatMessage(chatId, this.id, disableNotification)

  /**
   * Suspend function, which sends a filled-out payment form to the bot for final verification.
   *
   * @param chatId Chat identifier of the Invoice message.  
   * @param orderInfoId Identifier returned by ValidateOrderInfo, or an empty string.  
   * @param shippingOptionId Identifier of a chosen shipping option, if applicable.  
   * @param credentials The credentials chosen by user for payment.
   *
   * @return [TdApi.PaymentResult] Contains the result of a payment request.
   */


  /**
   * Suspend function, which updates the game score of the specified user in the game; for bots
   * only.
   *
   * @param chatId The chat to which the message with the game belongs.  
   * @param editMessage True, if the message should be edited.  
   * @param userId User identifier.  
   * @param score The new score.  
   * @param force Pass true to update the score even if it decreases. If the score is 0, the user
   * will be deleted from the high score table.
   *
   * @return [TdApi.Message] Describes a message.
   */
  suspend fun Message.setGameScore(
    chatId: Long,
    editMessage: Boolean,
    userId: Long,
    score: Int,
    force: Boolean
  ) = api.setGameScore(chatId, this.id, editMessage, userId, score, force)

  /**
   * Suspend function, which changes the user answer to a poll. A poll in quiz mode can be answered
   * only once.
   *
   * @param chatId Identifier of the chat to which the poll belongs.  
   * @param optionIds 0-based identifiers of answer options, chosen by the user. User can choose
   * more than 1 answer option only is the poll allows multiple answers.
   */
  suspend fun Message.setPollAnswer(chatId: Long, optionIds: IntArray?) = api.setPollAnswer(chatId,
      this.id, optionIds)

  /**
   * Suspend function, which stops a poll. A poll in a message can be stopped when the message has
   * canBeEdited flag set.
   *
   * @param chatId Identifier of the chat to which the poll belongs.  
   * @param replyMarkup The new message reply markup; for bots only.
   */
  suspend fun Message.stopPoll(chatId: Long, replyMarkup: ReplyMarkup?) = api.stopPoll(chatId,
      this.id, replyMarkup)

  /**
   * Suspend function, which validates the order information provided by a user and returns the
   * available shipping options for a flexible invoice.
   *
   * @param chatId Chat identifier of the Invoice message.  
   * @param orderInfo The order information, provided by the user.  
   * @param allowSave True, if the order information can be saved.
   *
   * @return [TdApi.ValidatedOrderInfo] Contains a temporary identifier of validated order
   * information, which is stored for one hour. Also contains the available shipping options.
   */
  suspend fun Message.validateOrderInfo(
    chatId: Long,
    orderInfo: OrderInfo?,
    allowSave: Boolean
  ) = api.validateOrderInfo(chatId, this.id, orderInfo, allowSave)
}
