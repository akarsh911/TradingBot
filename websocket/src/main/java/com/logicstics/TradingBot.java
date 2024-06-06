package com.logicstics;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TradingBot {

    private static final Logger logger = Logger.getLogger(TradingBot.class.getName());
    private static double triggerPrice;
    private static WazirXClient wazirXClient;

    public static void main(String[] args) throws URISyntaxException {

        logger.info("Starting TradingBot...");

        wazirXClient = new WazirXClient(new URI("wss://stream.wazirx.com/stream"));
        wazirXClient.connect();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter trigger price: ");
        triggerPrice = scanner.nextDouble();
        logger.info("Trigger price set to: " + triggerPrice);

        new Thread(() -> {
            while (true) {
                double lastPrice = wazirXClient.getLastPrice();
                if (lastPrice != 0.0) {
                    logger.info("Received data from socket: BTC/INR Price: " + lastPrice);
                    System.out.printf("Current BTC/INR Price: %.2f\n", lastPrice);
                    if (lastPrice <= triggerPrice) {
                        String buyPayload = PayloadPreparer.prepareBuyPayload(lastPrice);
                        System.out.println("Prepared Buy Order: " + buyPayload);
                        logger.info("Prepared Buy Order: " + buyPayload);
                    } else if (lastPrice >= triggerPrice) {
                        String sellPayload = PayloadPreparer.prepareSellPayload(lastPrice);
                        System.out.println("Prepared Sell Order: " + sellPayload);
                        logger.info("Prepared Sell Order: " + sellPayload);
                    }
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    logger.log(Level.SEVERE, "Thread was interrupted", e);
                }
            }
        }).start();
    }
}
