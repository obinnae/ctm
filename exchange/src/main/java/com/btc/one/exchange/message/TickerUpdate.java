package com.btc.one.exchange.message;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class TickerUpdate extends ExchangeUpdate {
    private Long trade_id;
    private Long sequence;
    private Instant time;
    private String product_id;
    private BigDecimal price;
    private String side;
    private BigDecimal last_size;
    private BigDecimal best_bid;
    private BigDecimal best_ask;
}
