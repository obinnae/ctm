package com.btc.one.exchange;

import com.btc.one.exchange.message.ChannelType;
import com.btc.one.exchange.message.Subscribe;

import java.util.*;
import java.util.stream.Collectors;

public class Subscription {
    private Map<ChannelType, Set<String>> productChannels = new HashMap<>();

    /**
     * Subscribe to exchange updates for multiple products on the same channel as the existing subscribed-to products.
     * @param products a collection of valid Crypto-Crypto/Crypto-Currency pair.
     * @param channelTypes a collection of channels to subscribe to the product on.
     * @return a JSON serializable request to subscribe to the new and existing products.
     */
    public Subscribe subscribe(Collection<? extends String> products, Collection<? extends ChannelType> channelTypes) {
        channelTypes.forEach(channelType -> this.productChannels.computeIfAbsent(channelType, ct -> new HashSet<>()).addAll(products));
        return new Subscribe(new HashSet<>(products), channelTypes.stream().map(ch -> ch.name).collect(Collectors.toSet()));
    }
}
