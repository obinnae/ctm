package com.btc.one.exchange.message;

import lombok.Data;

import java.util.Set;

@Data
public class Channel {
    ChannelType name;
    Set<String> product_ids;
}
