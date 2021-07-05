package com.mdgd.motiv8exam.models.network

import com.google.gson.Gson
import com.mdgd.motiv8exam.models.dto.Product
import com.neovisionaries.ws.client.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class NetworkImpl : Network {

    private val gson = Gson()

    override suspend fun getGroceries(): Flow<Product> {
        return callbackFlow {
            val ws = WebSocketFactory().createSocket(
                "ws://superdo-groceries.herokuapp.com/receive",
                5000
            )
            val listener = object : WebSocketAdapter() {
                override fun onTextMessage(
                    websocket: WebSocket?,
                    text: String?
                ) {
                    offer(gson.fromJson(text, Product::class.java))
                }

                override fun onError(websocket: WebSocket?, cause: WebSocketException?) {
                    error(Throwable(cause))
                }

                override fun onConnectError(
                    websocket: WebSocket?,
                    exception: WebSocketException?
                ) {
                    error(Throwable(exception))
                }

                override fun onDisconnected(
                    websocket: WebSocket?,
                    serverCloseFrame: WebSocketFrame?,
                    clientCloseFrame: WebSocketFrame?,
                    closedByServer: Boolean
                ) {
                    error(Throwable("Socket disconnected"))
                }
            }
            ws.addListener(listener)
            ws.connect()
            awaitClose {
                ws.removeListener(listener)
                ws.disconnect()
            }
        }
    }
}
