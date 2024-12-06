package org.blakasutha.imu_tcp.feature_tcp.data

import io.ktor.network.selector.SelectorManager
import io.ktor.network.sockets.Socket
import io.ktor.network.sockets.aSocket
import io.ktor.network.sockets.openReadChannel
import io.ktor.network.sockets.openWriteChannel
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.ByteWriteChannel
import io.ktor.utils.io.readUTF8Line
import io.ktor.utils.io.writeStringUtf8
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.blakasutha.imu_tcp.core.data.NetworkError
import org.blakasutha.imu_tcp.core.data.Result

class TcpClient(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): TcpDataSource {

    private var socket: Socket? = null
    private var selectorManager: SelectorManager? = null
    private var sendChannel: ByteWriteChannel? = null
    private var receiveChannel: ByteReadChannel? = null

    override suspend fun connect(host: String, port: Int): Result<Unit, NetworkError> {
        try {
            selectorManager = SelectorManager(dispatcher)
            socket = aSocket(selectorManager!!).tcp().connect(host, port)
            sendChannel = socket!!.openWriteChannel()
            receiveChannel = socket!!.openReadChannel()
            return Result.Success(Unit)
        } catch (e: Exception) {
            return Result.Error(NetworkError.INVALID_CONNECTION)
        }
    }

    override suspend fun send(data: String): Result<Unit, NetworkError> {
        if (socket == null || sendChannel == null ) {
            return Result.Error(NetworkError.INVALID_CONNECTION)
        }
        try {
            sendChannel!!.writeStringUtf8(data)
            return Result.Success(Unit)
        } catch (e: Exception) {
            return Result.Error(NetworkError.SERIALIZATION)
        }
    }

    override suspend fun receive(): Result<String, NetworkError> {
        if (socket == null || receiveChannel == null ) {
            return Result.Error(NetworkError.INVALID_CONNECTION)
        }
        try {
            val data = receiveChannel!!.readUTF8Line() ?: return Result.Error(NetworkError.CONNECTION_CLOSED)
            return Result.Success(data)
        } catch (e: Exception) {
            return Result.Error(NetworkError.SERIALIZATION)
        }
    }

    override suspend fun disconnect(): Result<Unit, NetworkError> {
        if (socket == null || receiveChannel == null || sendChannel == null) {
            return Result.Error(NetworkError.INVALID_CONNECTION)
        }
        try {
            withContext(dispatcher) {
                socket!!.close()
                selectorManager!!.close()
            }
            return Result.Success(Unit)
        } catch (e: Exception) {
            return Result.Error(NetworkError.INVALID_CONNECTION)
        }
    }
}