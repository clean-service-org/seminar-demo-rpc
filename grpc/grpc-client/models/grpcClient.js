const path = require('path');
const grpc = require('@grpc/grpc-js');
const protoLoader = require('@grpc/proto-loader');

// Load the protobuf definition
const PROTO_PATH = path.join(__dirname, '../proto', 'calculator.proto');
const packageDefinition = protoLoader.loadSync(PROTO_PATH, {
  keepCase: true,
  longs: String,
  enums: String,
  defaults: true,
  oneofs: true,
});

const calculatorProto =
  grpc.loadPackageDefinition(packageDefinition).calculator;

// Create gRPC client
const SERVER_HOST = 'localhost:9090';
const client = new calculatorProto.Calculator(
  SERVER_HOST,
  grpc.credentials.createInsecure()
);

module.exports = client;
