package com.btc.one.exchange;

import com.btc.one.exchange.message.ChannelType;
import com.btc.one.exchange.message.ExchangeUpdate;
import com.btc.one.exchange.message.Subscribe;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.WebSocket;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.util.Collection;

@Log
@RequiredArgsConstructor
public class ExchangeListener extends AbstractVerticle {

    private static final int MAX_WEB_SOCKET_FRAME_SIZE =  HttpClientOptions.DEFAULT_MAX_WEBSOCKET_FRAME_SIZE * 100;

    private final Subscription subscription;
    private final String exchangeAddress;
    private WebSocket webSocket;

    @Override
    public void start(Promise<Void> startPromise) {
        HttpClientOptions httpClientOptions = new HttpClientOptions().setMaxWebSocketFrameSize(MAX_WEB_SOCKET_FRAME_SIZE);
        HttpClient httpClient = vertx.createHttpClient(httpClientOptions);
        httpClient.webSocketAbs(exchangeAddress, null, null, null, ar -> {
            if (ar.failed()) {
                log.severe("Unable to connect to the exchange");
                log.severe(ar.cause().getMessage());
                startPromise.fail(ar.cause());
            } else {
                webSocket = ar.result();
                webSocket.closeHandler(closeAction -> log.fine("Closing exchange stream"));
                try {
                    super.start(startPromise);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Listen for exchange orders for a product and asynchronously handle/consume such updates.
     * @param products the products to subscribe to. Should be a collection of valid Crypto-Crypto or Crypto-Currency pairs.
     * @param channelTypes The channels to listen on.
     * @param exchangeUpdateHandler a handler to be asynchronously called when there are new order updates.
     */
    public void listen(Collection<? extends String> products, Collection<? extends ChannelType> channelTypes, Handler<ExchangeUpdate> exchangeUpdateHandler) {
        webSocket.textMessageHandler(update -> {
            ExchangeUpdate exchangeUpdate = ExchangeUpdate.fromString(update);
            exchangeUpdateHandler.handle(exchangeUpdate);
        });
        Subscribe subscribe = subscription.subscribe(products, channelTypes);
        webSocket.writeTextMessage(subscribe.toString());
    }
}
