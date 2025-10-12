# RPC PoC - Thrift Calculator

This repository contains two projects and uses Apache Thrift as the RPC framework:

- `calculator-server/` — Java Spring Boot Thrift server
- `calculator-client/` — Node.js MVC (Express + EJS) web client

Below are the minimal prerequisites and instructions to run both locally on macOS (zsh).

## Prerequisites

- Java 24+ and Maven
- Node.js 22+ and npm

## How to run

1. Start the Java Spring Boot Thrift server

```bash
cd calculator-server
mvn spring-boot:run
```

The server runs by default on port 8080 and exposes the Thrift servlet at `/calculator` (e.g. http://localhost:8080/calculator).

2. Start the Node.js MVC web client

```bash
cd calculator-client
npm install
npm run start
```

Open http://localhost:3000 in your browser to use the web UI.

Note: If you run the Java server on a different port or change the Thrift transport/protocol, update the Thrift client configuration in `calculator-client/models/thriftClient.js` so the client and server match.
