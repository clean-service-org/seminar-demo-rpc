const grpcClient = require('../models/grpcClient');

// Renders the main calculator page
exports.getCalculatorPage = (req, res) => {
  res.render('calculator', { result: null, error: null });
};

// Handles the form submission and makes the gRPC call
exports.postCalculation = async (req, res) => {
  try {
    const { num1, num2, operation } = req.body;
    const n1 = parseFloat(num1);
    const n2 = parseFloat(num2);

    // Create request object
    const request = { num1: n1, num2: n2 };

    // Make gRPC call based on operation
    const result = await new Promise((resolve, reject) => {
      const callback = (error, response) => {
        if (error) {
          reject(error);
        } else {
          resolve(response.result);
        }
      };

      switch (operation) {
        case 'add':
          grpcClient.Add(request, callback);
          break;
        case 'subtract':
          grpcClient.Subtract(request, callback);
          break;
        case 'multiply':
          grpcClient.Multiply(request, callback);
          break;
        case 'divide':
          grpcClient.Divide(request, callback);
          break;
        default:
          reject(new Error('Invalid operation'));
      }
    });

    res.render('calculator', { result: result, error: null });
  } catch (e) {
    let errorMessage = 'An unknown error occurred.';
    if (e.code) {
      // gRPC error
      errorMessage = e.details || e.message;
    } else {
      console.error(e);
    }

    res.render('calculator', { result: null, error: errorMessage });
  }
};
