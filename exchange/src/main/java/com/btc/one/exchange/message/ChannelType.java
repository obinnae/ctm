package com.btc.one.exchange.message;

import java.util.HashMap;
import java.util.Map;

public enum ChannelType {
    Ticker("ticker"), L2("level2");

    public final String name;

    private static final Map<String, ChannelType> channelTypes = new HashMap<>();

    static {
        for (ChannelType channelType : values()) {
            channelTypes.put(channelType.name, channelType);
        }
    }

    ChannelType(String name) {
        this.name = name;
    }

    public static ChannelType forName(String name) {
        if (!channelTypes.containsKey(name))
            throw new IllegalArgumentException("Invalid channel type");
        return channelTypes.get(name);
    }
}
