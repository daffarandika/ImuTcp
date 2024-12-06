package org.blakasutha.imu_tcp.feature_tcp.data

import android.util.Log
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

    override suspend fun connect(host: String, port: String): Result<Unit, NetworkError> {
        return withContext(dispatcher){
            try {
                selectorManager = SelectorManager(dispatcher)
                socket = aSocket(selectorManager!!).tcp().connect(host, port.toInt())
                sendChannel = socket!!.openWriteChannel()
                receiveChannel = socket!!.openReadChannel()
                return@withContext Result.Success(Unit)
            } catch (e: Exception) {
                return@withContext Result.Error(NetworkError.INVALID_CONNECTION)
            }
        }
    }

    override suspend fun send(data: String): Result<Unit, NetworkError> {
        if (socket == null || sendChannel == null ) {
            return Result.Error(NetworkError.INVALID_CONNECTION)
        }
        try {
            sendChannel!!.writeStringUtf8(data)
            sendChannel!!.flush()
            return Result.Success(Unit)
        } catch (e: Exception) {
            return Result.Error(NetworkError.SERIALIZATION)
        }
    }

    override suspend fun receive(): Result<String, NetworkError> {
        if (socket == null || receiveChannel == null ) {
            return Result.Error(NetworkError.INVALID_CONNECTION)
        }
        return withContext(dispatcher) {
            try {
                val data = receiveChannel!!.readUTF8Line() ?: return@withContext Result.Error(NetworkError.CONNECTION_CLOSED)
                return@withContext Result.Success(data)
            } catch (e: Exception) {
                return@withContext Result.Error(NetworkError.SERIALIZATION)
            }
        }
    }

    override suspend fun disconnect(): Result<Unit, NetworkError> {
        if (socket == null || receiveChannel == null || sendChannel == null) {
            return Result.Error(NetworkError.INVALID_CONNECTION)
        }
        return withContext(dispatcher){
            try {
                socket!!.close()
                selectorManager!!.close()
                return@withContext Result.Success(Unit)
            } catch (e: Exception) {
                return@withContext Result.Error(NetworkError.INVALID_CONNECTION)
            }
        }
    }
}