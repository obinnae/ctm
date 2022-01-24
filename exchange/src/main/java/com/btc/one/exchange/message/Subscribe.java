package com.btc.one.exchange.message;

import io.vertx.core.json.Json;
import lombok.Value;

import java.util.Set;

@Value
public class Subscribe {
    String type = "subscribe";
    Set<String> product_ids;
    Set<String> channels;

    @Override
    public String toString() {return Json.encode(this); }
}
