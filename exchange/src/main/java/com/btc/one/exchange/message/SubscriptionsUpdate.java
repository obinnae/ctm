package com.btc.one.exchange.message;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class SubscriptionsUpdate extends ExchangeUpdate {
    List<Channel> channels;
}
