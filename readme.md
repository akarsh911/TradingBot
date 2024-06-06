# TradingBot

TradingBot is a Java application that connects to the WazirX WebSocket API to fetch real-time trading data and prepares payloads for order operations based on a specified trigger price input by the user. The application provides a simple command-line interface (CLI) for user interaction.

## Features

1. **WebSocket Connection**: Establishes a connection to the WazirX WebSocket API and subscribes to market data for the BTC/INR currency pair.
2. **Payload Preparation**: 
   - Prepares a "buy" order payload when the market price hits or falls below the trigger price.
   - Prepares a "sell" order payload when the market price hits or exceeds the trigger price.
3. **User Interface**: 
   - CLI for inputting the trigger price.
   - Displays real-time market data and prepared payloads.
4. **Error Handling and Logging**: Manages potential issues during WebSocket communication and payload preparation, providing detailed logs for actions simulated by the application.

## Requirements

- Java 8 or later
- Maven 3.6.0 or later
- An internet connection for connecting to the WazirX WebSocket API

## Setup and Installation
 ```bash
 cd websocket
mvn clean install
mvn compile exec:java

```
## Input the Trigger Price:
When prompted, enter the trigger price for the BTC/INR market. The application will display real-time market data and prepared payloads for buy/sell orders based on the specified trigger price.

## Design Decisions and Libraries Used

## Design Decisions
- WebSocket Client: The application uses a WebSocket client to maintain a real-time connection with the WazirX API, ensuring the latest market data is always available.
- CLI: A simple command-line interface was chosen for ease of use and to avoid the complexities of a graphical user interface for this initial version.
- Payload Preparation: Prepared payloads are displayed to the user rather than being sent to the API to keep the application safe for demonstration and testing purposes.

# Directory
TradingBot
├── pom.xml
├── README.md   
├── websocket  
         ├──src
         ├── main
         │   └── java
         │       └── com
         │           └── logicstics
         │               ├── TradingBot.java
         │               ├── WazirXClient.java
         │               └── PayloadPreparer.java
         └── test
             └── java
                 └── com
                     └── logicstics
                         └── AppTest.java
    