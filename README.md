# RPC PoC - Calculator with gRPC & Java RPC

This repository demonstrates a simple calculator service using two different RPC technologies:

- **gRPC** with Protocol Buffers
- **Java RPC** (Remote Procedure Call using Java RMI)

## Project Structure

### gRPC Implementation

- `grpc/grpc-server/` — Java Spring Boot gRPC server (port 9090)
- `grpc/grpc-client/` — Node.js MVC (Express + EJS) web client (port 3001)
- `grpc/*/proto/calculator.proto` — Protocol Buffer service definition

### Java RPC Implementation

- `rpc/rpc-server/` — Java RPC server with Spring Boot (port 1099)
- `rpc/rpc-client/` — Java RPC web client with Spring Boot + Thymeleaf (port 3002)

## Prerequisites

- Java 17+ and Maven
- Node.js 18+ and npm

## How to run

### Option 1: gRPC Implementation

#### 1. Start the Java Spring Boot gRPC server

```bash
cd grpc/grpc-server
mvn spring-boot:run
```

The gRPC server runs on port 9090.

#### 2. Start the Node.js gRPC client

```bash
cd grpc/grpc-client
npm install
npm start
```

Open http://localhost:3001 in your browser to use the gRPC-based web UI.

### Option 2: Java RPC Implementation

#### 1. Start the RPC server

```bash
cd rpc/rpc-server
mvn spring-boot:run
```

The RPC server runs on port 1099 (default RMI registry port).

#### 2. Start the RPC web client

```bash
cd rpc/rpc-client
mvn spring-boot:run
```

Open http://localhost:3002 in your browser to use the RPC-based web UI.

## Architecture

### gRPC Implementation

The gRPC version uses:

- **Protocol Buffers** for service definition and serialization
- **gRPC** for RPC communication over HTTP/2
- **Java Spring Boot** for the server implementation
- **Node.js Express + EJS** for the web client with server-side rendering

### Java RPC Implementation

The RPC version uses:

- **Java Remote Interface** for service definition
- **Java RMI** for RPC communication over JRMP (Java Remote Method Protocol)
- **Spring Boot** for server and client applications
- **Spring MVC + Thymeleaf** for the web client with server-side rendering

Both implementations provide four operations: add, subtract, multiply, and divide. Division by zero is handled with appropriate error responses.

## Technology Comparison

| Feature                  | gRPC                                         | Java RPC                                     |
| ------------------------ | -------------------------------------------- | -------------------------------------------- |
| **Protocol**             | HTTP/2                                       | JRMP (Java Remote Method Protocol)           |
| **Serialization**        | Protocol Buffers                             | Java Serialization                           |
| **Language Support**     | Multi-language (Java, Node.js, Python, etc.) | Java only                                    |
| **Interface Definition** | `.proto` file (IDL)                          | Java interface extending `Remote`            |
| **Code Generation**      | Required (protoc compiler)                   | Not required (standard Java)                 |
| **Server Port**          | 9090 (customizable)                          | 1099 (default RMI registry)                  |
| **Client Port**          | 3001                                         | 3002                                         |
| **Client Type**          | Web UI (Express + EJS)                       | Web UI (Spring MVC + Thymeleaf)              |
| **Server Framework**     | Spring Boot                                  | Spring Boot                                  |
| **Performance**          | High (binary protocol, HTTP/2)               | Moderate (Java serialization overhead)       |
| **Use Case**             | Modern microservices, cross-language         | Legacy Java systems, simple distributed apps |

## Code Generation

### gRPC - Java (grpc-server)

The project uses the **Maven protobuf plugin** (`protobuf-maven-plugin`) to automatically generate Java code from the `.proto` file during compilation:

```bash
cd grpc/grpc-server
mvn clean compile
```

Generated files are located in:

- `target/generated-sources/protobuf/java/` - Protocol Buffer message classes
- `target/generated-sources/protobuf/grpc-java/` - gRPC service stubs and client classes

The plugin is configured in `grpc-server/pom.xml` with:

- `protoc` compiler (Protocol Buffers)
- `protoc-gen-grpc-java` plugin (gRPC Java code generator)

### gRPC - Node.js (grpc-client)

The Node.js client uses **`@grpc/proto-loader`** for dynamic runtime loading of the `.proto` file. No pre-generation is required:

```javascript
const protoLoader = require('@grpc/proto-loader');
const grpc = require('@grpc/grpc-js');

const packageDefinition = protoLoader.loadSync('calculator.proto', {
  keepCase: true,
  longs: String,
  enums: String,
  defaults: true,
  oneofs: true,
});

const proto = grpc.loadPackageDefinition(packageDefinition);
```

The proto file is loaded and parsed at runtime, making development faster and eliminating the need for a separate code generation step.

### Java RPC - No Code Generation Required

Java RPC (using RMI) does not require any code generation or IDL compilation:

1. Define a **Remote interface** that extends `java.rmi.Remote`
2. Implement the interface in a class extending `UnicastRemoteObject`
3. Both server and client share the same interface definition

This approach is simpler but locks you into Java-only ecosystem.
