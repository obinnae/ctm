package com.btc.one.exchange.message;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class L2Update extends ExchangeUpdate {
    String product_id;
    List<List<String>> changes;
    Instant time;
}
