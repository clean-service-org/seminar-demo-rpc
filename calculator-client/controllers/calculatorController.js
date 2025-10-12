const calculator = require('../gen-js/calculator_types');
const thriftClient = require('../models/thriftClient');

// Renders the main calculator page
exports.getCalculatorPage = (req, res) => {
    // Render the EJS view and pass initial data.
    // 'result' is null initially so the result box doesn't show.
    res.render('calculator', { result: null, error: null });
};

// Handles the form submission and makes the RPC call
exports.postCalculation = async (req, res) => {
    try {
        // 1. Get data from the form submission
        const { num1, num2, operation } = req.body;
        const n1 = parseFloat(num1);
        const n2 = parseFloat(num2);

        let result;

        // 2. Use the Model (thriftClient) to call the remote service
        switch (operation) {
            case 'add':
                result = await thriftClient.add(n1, n2);
                break;
            case 'subtract':
                result = await thriftClient.subtract(n1, n2);
                break;
            case 'multiply':
                result = await thriftClient.multiply(n1, n2);
                break;
            case 'divide':
                result = await thriftClient.divide(n1, n2);
                break;
            default:
                throw new Error('Invalid operation');
        }

        // 3. Render the View again, this time with the result
        res.render('calculator', { result: result, error: null });

    } catch (e) {
        // Handle errors, including the custom Thrift exception
        let errorMessage = 'An unknown error occurred.';
        if (e instanceof calculator.CalculationException) {
            errorMessage = e.message; // Use the message from our IDL-defined exception
        } else {
            console.error(e); // Log other unexpected errors
        }
        
        // 3b. Render the View again, this time with an error message
        res.render('calculator', { result: null, error: errorMessage });
    }
};
