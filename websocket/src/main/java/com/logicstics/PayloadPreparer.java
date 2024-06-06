package com.logicstics;

public class PayloadPreparer {

    public static String prepareBuyPayload(double price) {
        return String.format("{\"type\":\"buy\",\"price\":%.2f}", price);
    }

    public static String prepareSellPayload(double price) {
        return String.format("{\"type\":\"sell\",\"price\":%.2f}", price);
    }

    public static String prepareCancelPayload(String orderId) {
        return String.format("{\"type\":\"cancel\",\"orderId\":\"%s\"}", orderId);
    }
}
