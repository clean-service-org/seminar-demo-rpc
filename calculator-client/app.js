const express = require('express');
const bodyParser = require('body-parser');
const path = require('path');
const calculatorRoutes = require('./routes/calculatorRoutes');

const app = express();
const PORT = 3000;

// --- Middleware Configuration ---

// Set the view engine to EJS
app.set('view engine', 'ejs');
// Tell Express where to find the view files
app.set('views', path.join(__dirname, 'views'));

// Use body-parser to read data from HTML forms
app.use(bodyParser.urlencoded({ extended: true }));

// --- Routes ---
// Tell the app to use the routes defined in our routes file.
app.use('/', calculatorRoutes);

// --- Start the Server ---
app.listen(PORT, () => {
    console.log(`Node.js client server running on http://localhost:${PORT}`);
    console.log('Ensure the Java Thrift server is also running.');
});
