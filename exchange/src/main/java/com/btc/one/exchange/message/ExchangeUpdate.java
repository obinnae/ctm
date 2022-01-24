package com.btc.one.exchange.message;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.vertx.core.json.Json;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = SubscriptionsUpdate.class, name = "subscriptions"),
        @JsonSubTypes.Type(value = L2Update.class, name = "l2update"),
        @JsonSubTypes.Type(value = TickerUpdate.class, name = "ticker")
})
public abstract class ExchangeUpdate extends ExchangeMessage {
    public static ExchangeUpdate fromString(String responseString) {
        return Json.decodeValue(responseString, ExchangeUpdate.class);
    }
}
