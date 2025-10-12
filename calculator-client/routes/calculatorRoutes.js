const express = require('express');
const router = express.Router();
const calculatorController = require('../controllers/calculatorController');

// When a user goes to the homepage (GET /), show the calculator page.
router.get('/', calculatorController.getCalculatorPage);

// When a user submits the form (POST /calculate), handle the calculation.
router.post('/calculate', calculatorController.postCalculation);

module.exports = router;
