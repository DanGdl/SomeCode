package com.mdgd.motiv8exam.models.network

import com.google.gson.Gson
import com.mdgd.motiv8exam.models.dto.Product
import com.neovisionaries.ws.client.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.sendBlocking

class NetworkImpl : Network {

    private val gson = Gson()

    override suspend fun getGroceries(): Channel<Product> {
        // TODO connect callbackFlow?
        val channel = Channel<Product>(Channel.Factory.CONFLATED)
        try {
            val ws = WebSocketFactory().createSocket(
                "ws://superdo-groceries.herokuapp.com/receive",
                5000
            )
            ws.addListener(object : WebSocketAdapter() {
                override fun onTextMessage(
                    websocket: WebSocket?,
                    text: String?
                ) {
                    channel.sendBlocking(gson.fromJson(text, Product::class.java))
                }

                override fun onConnectError(
                    websocket: WebSocket?,
                    exception: WebSocketException?
                ) {
                    channel.close(exception)
                }

                override fun onDisconnected(
                    websocket: WebSocket?,
                    serverCloseFrame: WebSocketFrame?,
                    clientCloseFrame: WebSocketFrame?,
                    closedByServer: Boolean
                ) {
                    channel.close(Throwable("Socket disconnected"))
                }
            })
            ws.connect()
        } catch (e: Throwable) {
            channel.close(e)
        }
        return channel
    }
}
