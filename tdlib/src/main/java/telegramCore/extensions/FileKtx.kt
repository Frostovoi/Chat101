//
// NOTE: THIS FILE IS AUTO-GENERATED by the "ExtensionsGenerator".kt
// See: https://github.com/tdlibx/td-ktx-generator/
//
package telegramCore.extensions

import org.drinkless.td.libcore.telegram.TdApi
import org.drinkless.td.libcore.telegram.TdApi.File
import telegramCore.core.TelegramFlow
import telegramCore.coroutines.cancelDownloadFile
import telegramCore.coroutines.cancelUploadFile
import telegramCore.coroutines.deleteFile
import telegramCore.coroutines.downloadFile
import telegramCore.coroutines.getAttachedStickerSets
import telegramCore.coroutines.getFile
import telegramCore.coroutines.getFileDownloadedPrefixSize
import telegramCore.coroutines.readFilePart

/**
 * Interface for access [TdApi.File] extension functions. Can be used alongside with other extension
 * interfaces of the package. Must contain [TelegramFlow] instance field to access its functionality
 */
interface FileKtx : BaseKtx {
  /**
   * Instance of the [TelegramFlow] connecting extensions to the Telegram Client
   */
  override val api: TelegramFlow

  /**
   * Suspend function, which stops the downloading of a file. If a file has already been downloaded,
   * does nothing.
   *
   * @param onlyIfPending Pass true to stop downloading only if it hasn't been started, i.e. request
   * hasn't been sent to server.
   */
  suspend fun File.cancelDownload(onlyIfPending: Boolean) = api.cancelDownloadFile(this.id,
      onlyIfPending)

  /**
   * Suspend function, which stops the uploading of a file. Supported only for files uploaded by
   * using uploadFile. For other files the behavior is undefined.
   */
  suspend fun File.cancelUpload() = api.cancelUploadFile(this.id)

  /**
   * Suspend function, which deletes a file from the TDLib file cache.
   */
  suspend fun File.delete() = api.deleteFile(this.id)

  /**
   * Suspend function, which downloads a file from the cloud. Download progress and completion of
   * the download will be notified through updateFile updates.
   *
   * @param priority Priority of the download (1-32). The higher the priority, the earlier the file
   * will be downloaded. If the priorities of two files are equal, then the last one for which
   * downloadFile was called will be downloaded first.  
   * @param offset The starting position from which the file should be downloaded.  
   * @param limit Number of bytes which should be downloaded starting from the &quot;offset&quot;
   * position before the download will be automatically cancelled; use 0 to download without a limit.  
   * @param synchronous If false, this request returns file state just after the download has been
   * started. If true, this request returns file state only after the download has succeeded, has
   * failed, has been cancelled or a new downloadFile request with different offset/limit parameters
   * was sent.
   *
   * @return [TdApi.File] Represents a file.
   */
  suspend fun File.download(
    priority: Int,
    offset: Int,
    limit: Int,
    synchronous: Boolean
  ) = api.downloadFile(this.id, priority, offset, limit, synchronous)

  /**
   * Suspend function, which returns a list of sticker sets attached to a file. Currently only
   * photos and videos can have attached sticker sets.
   *
   *
   * @return [TdApi.StickerSets] Represents a list of sticker sets.
   */
  suspend fun File.getAttachedStickerSets() = api.getAttachedStickerSets(this.id)

  /**
   * Suspend function, which returns information about a file; this is an offline request.
   *
   *
   * @return [TdApi.File] Represents a file.
   */
  suspend fun File.get() = api.getFile(this.id)

  /**
   * Suspend function, which returns file downloaded prefix size from a given offset.
   *
   * @param offset Offset from which downloaded prefix size should be calculated.
   *
   * @return [TdApi.Count] Contains a counter.
   */
  suspend fun File.getDownloadedPrefixSize(offset: Int) = api.getFileDownloadedPrefixSize(this.id,
      offset)

  /**
   * Suspend function, which reads a part of a file from the TDLib file cache and returns read
   * bytes. This method is intended to be used only if the client has no direct access to TDLib's file
   * system, because it is usually slower than a direct read from the file.
   *
   * @param offset The offset from which to read the file.  
   * @param count Number of bytes to read. An error will be returned if there are not enough bytes
   * available in the file from the specified position. Pass 0 to read all available data from the
   * specified position.
   *
   * @return [TdApi.FilePart] Contains a part of a file.
   */
  suspend fun File.readPart(offset: Int, count: Int) = api.readFilePart(this.id, offset, count)
}
