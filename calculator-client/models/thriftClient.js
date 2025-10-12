const thrift = require('thrift');

// The types file MUST be required before the service file.
const ttypes = require('../gen-js/calculator_types');
const Calculator = require('../gen-js/Calculator');

// --- Configuration ---
const SERVER_HOST = 'localhost'; // The Java server is running on the same machine
const SERVER_PORT = 8080;
const SERVER_PATH = '/calculator';

// --- Create the Thrift Connection and Client for Node.js ---
// This uses the standard Node.js HTTP connection method.
const connection = thrift.createHttpConnection(SERVER_HOST, SERVER_PORT, {
  transport: thrift.TBufferedTransport,
  protocol: thrift.TJSONProtocol,
  path: SERVER_PATH
});

// **FIX:** We must pass the Calculator.Client constructor, not the whole module.
const client = thrift.createHttpClient(Calculator.Client, connection);

// Log connection errors
connection.on('error', (err) => {
  console.error('Thrift connection error:', err);
});

// Export the configured client so our controllers can use it.
module.exports = client;

