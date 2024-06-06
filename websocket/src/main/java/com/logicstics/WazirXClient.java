package com.logicstics;

import java.net.URI;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WazirXClient extends WebSocketClient {

    private static final Logger logger = LoggerFactory.getLogger(WazirXClient.class);
    private ObjectMapper objectMapper = new ObjectMapper();
    private double lastPrice;

    public WazirXClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        logger.info("Connected to WebSocket");
        subscribeToMarketData("btcinr");
    }

    @Override
    public void onMessage(String message) {
        try {
            JsonNode jsonNode = objectMapper.readTree(message);
            if (jsonNode.has("lastPrice")) {
                lastPrice = jsonNode.get("lastPrice").asDouble();
                logger.info("BTC/INR: " + lastPrice);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        logger.info("Connection closed: " + reason);
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
    }

    private void subscribeToMarketData(String market) {
        String subscriptionMessage = String.format("{\"event\":\"subscribe\",\"streams\":[\"%s@ticker\"]}", market);
        send(subscriptionMessage);
    }

    public double getLastPrice() {
        return lastPrice;
    }
}
