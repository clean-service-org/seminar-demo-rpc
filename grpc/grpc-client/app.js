const express = require('express');
const bodyParser = require('body-parser');
const path = require('path');
const calculatorRoutes = require('./routes/calculatorRoutes');

const app = express();
const PORT = 3001;

// --- Middleware Configuration ---
app.set('view engine', 'ejs');
app.set('views', path.join(__dirname, 'views'));
app.use(bodyParser.urlencoded({ extended: true }));

// --- Routes ---
app.use('/', calculatorRoutes);

// --- Start the Server ---
app.listen(PORT, () => {
  console.log(`gRPC Node.js client server running on http://localhost:${PORT}`);
  console.log('Ensure the gRPC Java server is also running on port 9090.');
});
